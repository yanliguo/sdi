package org.sdi.example.clazz;

/**
 * Created by guoyanli on 12/30/15.
 */
public class HiveCoupledDao extends AbstractCoupledDao {

    public HiveCoupledDao(String hosts, int maxConn) {
        super(HiveCoupledDao.hiveConnectorInstance(hosts, maxConn));
    }

    public static HiveConnector hiveConnectorInstance(String hosts, int maxConn) {
        HiveConnector connector = new HiveConnector();

        connector.setHosts(hosts);
        connector.setMaxConnector(maxConn);
        return connector;
    }
}
