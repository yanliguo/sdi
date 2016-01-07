package org.sdi.context;

import org.sdi.factory.BeanFactory;

/**
 * @author guoyanli
 */
public interface IApplicationContext {
    /**
     *
     * @return bean factory of application context
     */
    BeanFactory getBeanFactory();

}
