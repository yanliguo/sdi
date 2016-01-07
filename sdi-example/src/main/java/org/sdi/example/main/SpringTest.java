package org.sdi.example.main;

import org.sdi.factory.BeanFactory;
import org.sdi.factory.JsonResourceBeanFactory;
import org.sdi.example.clazz.AbstractCoupledDao;
import org.sdi.example.clazz.HiveCoupledDao;
import org.sdi.example.clazz.IConnector;
import org.sdi.example.clazz.IDao;

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
