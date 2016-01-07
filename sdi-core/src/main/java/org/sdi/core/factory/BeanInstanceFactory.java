package org.sdi.core.factory;

import org.sdi.common.InstanceFactory;
import org.sdi.core.*;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by guoyanli on 12/30/15.
 */
public class BeanInstanceFactory {

    public static Object instance(BeanDefinition definition) throws Exception {
        if (definition instanceof ConstructedBean) {
            return constructBeanInstance((ConstructedBean)definition);
        } else if (definition instanceof FactoryBean) {
            return factoryBeanInstance((FactoryBean) definition);
        } else {
            // todo throws exceptions
            return null;
        }
    }

    private static Object constructBeanInstance(ConstructedBean bean) throws Exception{
        if (bean == null || bean.getClassName() == null) {
            return null;
        }
        return InstanceFactory.newInstance(bean.getClassName());
    }

    private static Object factoryBeanInstance(FactoryBean bean) throws Exception {
        if (bean == null || bean.getFactoryMethod() == null) {
            return null;
        }

        return InstanceFactory.factoryInstance(bean.getClassName(), bean.getFactoryMethod());
    }

    public static void injection(Object objectInstance,
                                 String instanceClassName,
                                 Map<String, Object> propertyToObject,
                                 Map<String, Property> properties) throws Exception {
        Class<?> clazz = Class.forName(instanceClassName);
        if (!clazz.isInstance(objectInstance)) {
            throw new Exception("object is not an instance of " + instanceClassName);
        }
        // todo if autowared ? setField
        // else call setMethod
        for (Map.Entry<String, Object> entry : propertyToObject.entrySet()) {
            Property property = properties.get(entry.getKey());
            if (property.getIsAutowared()) {
                // todo
            } else {
                Field field = clazz.getField(property.getPropertyName());
                if (field == null) {
                    throw new Exception("not found field " + property.getPropertyName() +
                                                " for class: " + instanceClassName);
                }
                // inject value
                if (property.getPropertyValue() != null) {
                    String fieldType = property.getPropertyType();
                    if (fieldType == null) {
                        Class<?> fieldClass = field.getDeclaringClass();
                        fieldType = fieldClass.getName();
                    }
                    System.out.println("field: " + property.getPropertyName() + " type: " + fieldType);
                } else { // inject ref
                    String refBeanId = property.getPropertyRef();
                    if (refBeanId == null) {
                        throw new Exception("value & ref are both missing");
                    }

                }

            }
        }

    }
}
