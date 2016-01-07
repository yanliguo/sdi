package org.sdi.factory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.sdi.core.BeanDefinition;
import org.sdi.core.factory.BeanJsonDefinitionFactory;
import org.sdi.except.BeanDefinitionException;
import org.sdi.except.BeanException;


/**
 * Abstract JsonBeanFactory
 * @author guoyanli
 */
abstract public class AbstractJsonBeanFactory extends AbstractBeanFactory {
    public static final String BEANS_KEY = "beans";
    public static final String IMPORT_KEY = "import";
    public static final String SCAN_KEY = "scans";

    protected JsonObject root;

    public AbstractJsonBeanFactory(JsonObject root) throws Exception {
        super();
        this.root = root;
        initBeanDefinitions();
        initialBeans();
    }

    @Override
    protected void initBeanDefinitions() throws BeanDefinitionException {
        JsonArray beansArray = this.root.getAsJsonArray(BEANS_KEY);
        if (beansArray != null) {
            for (JsonElement ele : beansArray) {
                BeanDefinition definition = BeanJsonDefinitionFactory.getBeanDefinition(ele);
                registerBean(definition);
            }
        }
        // todo imports

        // todo scans
    }
}
