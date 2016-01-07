package org.sdi.core.factory;

import org.apache.commons.lang3.StringUtils;
import org.sdi.core.Property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * inject property into instance
 *
 * @author guoyanli
 */
public class PropertyInject {

    private static final Map<String, Method> simpleValueInjectHandler = new HashMap<String, Method>();
    private static final Set<String> simpleProperties = new HashSet<String>();
    static {
        try {
            Method integerMethod = PropertyInject.class.getDeclaredMethod("injectInteger", String.class, Object.class, String.class, String.class);
            simpleValueInjectHandler.put(Integer.class.getName().toLowerCase(), integerMethod);
            simpleValueInjectHandler.put(Integer.class.getSimpleName().toLowerCase(), integerMethod);
            simpleProperties.add(Integer.class.getSimpleName());

            Method stringInjectionMethod = PropertyInject.class.getDeclaredMethod("injectString", String.class, Object.class, String.class, String.class);
            simpleValueInjectHandler.put(String.class.getName().toLowerCase(), stringInjectionMethod);
            simpleValueInjectHandler.put(String.class.getSimpleName().toLowerCase(), stringInjectionMethod);
            simpleProperties.add(String.class.getSimpleName());

            // todo more
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void inject(Property property,
                              Object instance,
                              Object ref,
                              String instanceClass) throws Exception {
        if (property.getPropertyType() == null) {
            Field propertyField = Class.forName(instanceClass).getDeclaredField(property.getPropertyName());
            if (propertyField == null) {
                return;
            }
            property.setPropertyType(propertyField.getType().getName());
        }
        // todo isSimple
        if (simpleValueInjectHandler.containsKey(property.getPropertyType().toLowerCase())) {
            Method method = simpleValueInjectHandler.get(property.getPropertyType().toLowerCase());
            if (method == null) {
                throw new Exception("unsupported handler for property class " + property.getPropertyType());
            }
            method.invoke(null, property.getPropertyName(), instance, property.getPropertyValue(), instanceClass);
        } else {
            injectObject(property.getPropertyName(), instance, ref, instanceClass, Class.forName(property.getPropertyType()));
        }

    }

    public static void injectInteger(String property, Object instance, String simpleValue, String instanceClass) {
        directAccess(instance, property, Integer.getInteger(simpleValue), instanceClass, Integer.class);
    }

    public static void injectString(String property, Object instance, String val, String instanceClass) {
        directAccess(instance, property, val, instanceClass, String.class);
    }

    public static void injectObject(String property, Object owner, Object ref, String instanceClass, Class<?> propertyClazz) {
        directAccess(owner, property, ref, instanceClass, propertyClazz);

    }

    private static void directAccess(Object owner, String property, Object value, String instanceClass, Class<?> propertyClazz) {
        try {
            String setMethodName = "set" + StringUtils.capitalize(property);
            Method method = Class.forName(instanceClass).getDeclaredMethod(setMethodName, propertyClazz);
            if (method == null) {
                System.out.println("method not found for " + setMethodName + " at class " + instanceClass);
            } else {
                method.invoke(owner, value);
            }

        } catch (Exception e) {
            // todo
            e.printStackTrace();
        }
    }

}
