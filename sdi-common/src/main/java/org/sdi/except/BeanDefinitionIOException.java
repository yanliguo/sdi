package org.sdi.except;

/**
 * Created by guoyanli on 12/29/15.
 */
public class BeanDefinitionIOException extends BeanDefinitionException {
    public BeanDefinitionIOException(String ioResource) {
        super("io exception on bean definition resource: " + ioResource);
    }
}
