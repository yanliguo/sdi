package org.sdi.except;

/**
 * Created by guoyanli on 12/29/15.
 */
public class BeanInitializeFailedException extends BeanException {
    public BeanInitializeFailedException(String id) {
        super("initialize failed on bean id: " + id);
    }
}
