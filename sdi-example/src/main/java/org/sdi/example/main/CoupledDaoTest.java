package org.sdi.example.main;

import org.sdi.example.clazz.HbaseCoupledDao;
import org.sdi.example.clazz.HiveCoupledDao;
import org.sdi.example.clazz.IDao;

/**
 * Created by guoyanli on 12/30/15.
 */
public class CoupledDaoTest {

    public static void main(String[] args) {

        // new hive dao instance
        IDao hiveDao = new HiveCoupledDao("dx-hive01,dx-hive02", 1000);

        hiveDao.getConnector().tell();
        hiveDao.getConnector().execute("hive sql");

        // new hbase dao instance
        IDao hbaseDao = new HbaseCoupledDao("cq-hbase01,cq-hbase02", 200);

        hbaseDao.getConnector().tell();
        hbaseDao.getConnector().execute("hbase statement");
    }
}
