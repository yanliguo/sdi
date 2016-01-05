package org.sdi.common;

import org.sdi.core.BeanDefinition;
import org.sdi.core.ConstructedBean;
import org.sdi.core.FactoryBean;
import org.sdi.except.NotSupportedBeanException;

/**
 * Created by guoyanli on 12/29/15.
 */
public class BeanUtils {

    /**
     * create an object using bean definition
     * @param beanDefinition
     * @return
     */
    public static Object getBean(BeanDefinition beanDefinition) {
        if (beanDefinition instanceof FactoryBean) {
            return getFactoryBean((FactoryBean)beanDefinition);
        } else if (beanDefinition instanceof ConstructedBean) {
            return getConstructedBean((ConstructedBean)beanDefinition);
        } else {
            throw new NotSupportedBeanException(beanDefinition.toString());
        }
    }

    private static Object getFactoryBean(FactoryBean bean) {
        return null;
    }

    private static Object getConstructedBean(ConstructedBean bean) {
        return null;
    }
}
