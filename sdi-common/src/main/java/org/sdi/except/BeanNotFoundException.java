package org.sdi.except;

/**
 * Created by guoyanli on 12/29/15.
 */
public class BeanNotFoundException extends BeanException {
    public BeanNotFoundException(String beanId) {
        super("using bean id: " + beanId);
    }

    public BeanNotFoundException(Class<?> clazz) {
        super("using class: " + clazz.getName());
    }
}
