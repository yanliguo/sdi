package org.sdi.factory;

import org.sdi.common.ObjectWrapper;
import org.sdi.core.factory.PropertyInject;
import org.sdi.core.Autoware;
import org.sdi.core.BeanDefinition;
import org.sdi.core.Property;
import org.sdi.core.factory.BeanInstanceFactory;
import org.sdi.except.BeanDefinitionException;
import org.sdi.except.BeanException;
import org.sdi.except.BeanInitializeFailedException;
import org.sdi.except.BeanNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by guoyanli on 12/28/15.
 */
abstract public class AbstractBeanFactory implements BeanFactory {
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    protected Map<String, ObjectWrapper> beanObject = new ConcurrentHashMap<String, ObjectWrapper>();

    public AbstractBeanFactory() {

    }

    public void registerBean(BeanDefinition beanDefinition) {
        if (beanDefinition != null) {
            this.beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
        }
    }

    public void initialBean(String id) throws BeanException {
        BeanDefinition definition = beanDefinitionMap.get(id);
        if (definition == null) {
            throw new BeanNotFoundException(id);
        }

        initialBean(id, definition);
    }

    public void initialBean(String id, BeanDefinition definition) throws BeanException {
        // todo 1. instance

        // todo 2. dependency injection

    }


    /**
     * how to implement bean definition initialization
     * @throws BeanDefinitionException
     */
    abstract protected void initBeanDefinitions() throws BeanDefinitionException;

    /**
     * implement bean initializations
     * @throws BeanException
     */
    protected void initialBeans() throws Exception {
        // todo 1. initialize instance
        for (Map.Entry<String, BeanDefinition> entry : this.beanDefinitionMap.entrySet()) {
            instanizeBean(entry.getKey(), entry.getValue());
        }
        // todo 2. inject dependency & properties
        for (Map.Entry<String, BeanDefinition> entry : this.beanDefinitionMap.entrySet()) {
            injectBeanDependencies(entry.getKey(), entry.getValue());
        }
    }

    private void instanizeBean(String beanId, BeanDefinition definition) throws Exception {
        Object instance = BeanInstanceFactory.instance(definition);
        if (instance != null) {
            ObjectWrapper wrapper = new ObjectWrapper(instance);
            wrapper.triedToInitial = Boolean.FALSE;
            this.beanObject.put(beanId, wrapper);
        }
    }

    private void injectBeanDependencies(String beanId, BeanDefinition definition) throws Exception {
        ObjectWrapper ownWrapper = this.beanObject.get(beanId);
        if (ownWrapper == null) {
            // todo throws exception
            return;
        }
        List<Property> properties = definition.getProperties();
        Map<String, Object> propertyToObject = new HashMap<String, Object>();
        for (Property p : properties) {
            if (p.getIsAutowared() && p.getAutowareType() != Autoware.None) {
                // findByName or findByClass
            } else {
                if (p.getPropertyValue() != null) {
                    propertyToObject.put(p.getPropertyName(), p.getPropertyValue());
                } else if (p.getPropertyRef() != null) {
                    propertyToObject.put(p.getPropertyName(), getBean(p.getPropertyRef()));
                }
                String propertyType = p.getPropertyType();
                Object ref = null;
                if (p.getPropertyRef() != null) {
                    ref = getBean(p.getPropertyRef());
                }

                PropertyInject.inject(p, ownWrapper.object, ref, definition.getClassName());
            }
        }

        // do injection
        // BeanInstanceFactory.injection(ownWrapper.object, propertyToObject);
    }

    public Object getBean(Class<?> clazz) throws BeanException {
        String clazzName = clazz.getName();
        for (Map.Entry<String, BeanDefinition> definitionEntry : beanDefinitionMap.entrySet()) {
            if (definitionEntry.getValue().getClassName().equals(clazzName)) {
                return getBean(definitionEntry.getKey(), definitionEntry.getValue());
            }
        }
        throw new BeanNotFoundException(clazz);
    }

    public List<Object> getBeans(Class<?> clazz) throws BeanException {
        List<Object> ret = new ArrayList<Object>();
        String clazzName = clazz.getName();
        for (Map.Entry<String, BeanDefinition> definitionEntry : beanDefinitionMap.entrySet()) {
            if (definitionEntry.getValue().getClassName().equals(clazzName)) {
                try {
                    ret.add(getBean(definitionEntry.getKey(), definitionEntry.getValue()));
                } catch (BeanException e) {
                    // ignore
                }
            }
        }
        return ret;
    }

    public Object getBean(String id) throws BeanException {
        BeanDefinition definition = beanDefinitionMap.get(id);
        if (definition == null) {
            throw new BeanNotFoundException(id);
        }

        return getBean(id, definition);
    }

    public Object getBean(String id, BeanDefinition definition) throws BeanException {
        ObjectWrapper objectWrapper = beanObject.get(id);
        if (objectWrapper == null && definition.getIsLazy()) {
            initialBean(id, definition);
        }

        objectWrapper = beanObject.get(id);
        if (objectWrapper == null || (objectWrapper.object == null && objectWrapper.triedToInitial)) {
            throw new BeanInitializeFailedException(id);
        }

        return objectWrapper.object;
    }
}
