package org.sdi.core.factory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.sdi.core.BeanDefinition;
import org.sdi.core.ConstructedBean;
import org.sdi.core.FactoryBean;
import org.sdi.core.Property;
import org.sdi.factory.JsonPropertyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guoyanli
 */
public class BeanJsonDefinitionFactory {
    /**
     *
     * @param ele
     * @return
     */
    public static BeanDefinition getBeanDefinition(JsonElement ele) {
        JsonObject beanRoot = ele.getAsJsonObject();
        if (beanRoot.has(FactoryBean.FACTORY_METHOD_NAME)) {
            return getFactoryBeanDefinition(beanRoot);
        } else {
            return getConstructedBeanDefinition(beanRoot);
        }
        // todo throws exception for un-supported
    }

    private static FactoryBean getFactoryBeanDefinition(JsonObject root) {
        FactoryBean bean = new FactoryBean();
        buildCommon(root, bean);
        if (root.has(FactoryBean.FACTORY_METHOD_NAME)) {
            bean.setFactoryMethod(root.get(FactoryBean.FACTORY_METHOD_NAME).getAsString());
        }
        return bean;
    }

    private static BeanDefinition getConstructedBeanDefinition(JsonObject root) {
        ConstructedBean bean = new ConstructedBean();
        buildCommon(root, bean);

        if (root.has(ConstructedBean.INITIAL_METHOD_NAME)) {
            bean.setInitMethod(root.get(ConstructedBean.INITIAL_METHOD_NAME).getAsString());
        }
        if (root.has(ConstructedBean.CONSTRUCT_ARGS_NAME)) {
            // todo
            // bean.appendConstructArgs();
        }
        return bean;
    }

    private static void buildCommon(JsonObject root, BeanDefinition definition) {
        if (root.has(BeanDefinition.CLASS_NAME)) {
            definition.setClassName(root.get(BeanDefinition.CLASS_NAME).getAsString());
        }

        if (root.has(BeanDefinition.ID_NAME)) {
            definition.setId(root.get(BeanDefinition.ID_NAME).getAsString());
        }

        if (root.has(BeanDefinition.LAZY_NAME)) {
            definition.setIsLazy(root.get(BeanDefinition.LAZY_NAME).getAsBoolean());
        }

        if (root.has(BeanDefinition.PROPERTIES_NAME)) {
            JsonArray propertyArray = root.get(BeanDefinition.PROPERTIES_NAME).getAsJsonArray();

            List<Property> properties = new ArrayList<Property>();
            for (JsonElement ele : propertyArray) {
                Property p = JsonPropertyFactory.getProperty(ele.getAsJsonObject());
                if (p != null) {
                    properties.add(p);
                }
            }
            definition.setProperties(properties);
        }
    }
}
