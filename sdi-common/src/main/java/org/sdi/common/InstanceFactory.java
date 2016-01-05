package org.sdi.common;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by guoyanli on 12/26/15.
 */
public class InstanceFactory {
    // todo class maybe interface , abstract ...

    public static Object newInstance(String clazzName) throws Exception {
        return Class.forName(clazzName).newInstance();
    }

    public static Object newInstance(String clazzName, Map<String, Object> args) throws Exception {
        // todo
        Class<?> clazz = Class.forName(clazzName);
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            // constructor.
        }
        return Class.forName(clazzName).getConstructors();
    }

    public static Object factoryInstance(String clazzName, String factoryMethodName) throws Exception {
        Class<?> clazz = Class.forName(clazzName);
        Method[] methods = clazz.getDeclaredMethods();
        Method method = null;
        for (int idx = 0; idx < methods.length; idx ++ ) {
            if (methods[idx].getName().equals(factoryMethodName)) {
                // todo constructed-args
                method = methods[idx];
            }
        }
        if (method == null) {
            // todo throws exception
            return null;
        }
        Class<?> returnType = method.getReturnType();
        if (returnType.getName().equals("void")) {
            // todo throws exception
            return null;
        }
        Object[] args = new Object[]{};
        // todo args
        return method.invoke(null, args);
    }
}
