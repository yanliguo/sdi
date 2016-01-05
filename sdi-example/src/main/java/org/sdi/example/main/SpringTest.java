package org.di.test;

import org.di.factory.BeanFactory;
import org.di.factory.JsonResourceBeanFactory;
import org.di.test.clazz.AbstractCoupledDao;
import org.di.test.clazz.HiveCoupledDao;
import org.di.test.clazz.IConnector;
import org.di.test.clazz.IDao;

/**
 * @author guoyanli
 */
public class SpringTest {
    public static void main(String[] args) {
        try {
            BeanFactory beanFactory = JsonResourceBeanFactory.getFactory("beans/bean.json");

            IConnector hbaseConnector = (IConnector)beanFactory.getBean("hbaseConnector");
            hbaseConnector.tell();

            Object hiveDao = beanFactory.getBean("hiveDao");
            System.out.println(hiveDao instanceof AbstractCoupledDao);
            System.out.println(hiveDao instanceof IDao);

            ((AbstractCoupledDao)hiveDao).getConnector().tell();

            IDao hbaseDao = (IDao)beanFactory.getBean("hbaseDao");
            hbaseDao.getConnector().tell();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
