package org.sdi.factory;

import org.sdi.except.BeanException;

import java.util.List;

/**
 * Created by guoyanli on 12/28/15.
 */
public interface BeanFactory {
    /**
     *
     * @param id
     * @return
     * @throws BeanException
     */
    Object getBean(String id) throws BeanException;

    /**
     *
     * @param clazz
     * @return
     * @throws BeanException
     */
    Object getBean(Class<?> clazz) throws BeanException;

    /**
     *
     * @param clazz
     * @return
     * @throws BeanException
     */
    List<Object> getBeans(Class<?> clazz) throws BeanException;
}
