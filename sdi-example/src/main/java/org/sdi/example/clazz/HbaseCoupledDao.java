package org.sdi.example.clazz;

/**
 * Created by guoyanli on 12/30/15.
 */
public class HbaseCoupledDao extends AbstractCoupledDao {

    public HbaseCoupledDao(String hosts, int maxWaiting) {
        super(HbaseCoupledDao.hbaseConnectorInstance(hosts, maxWaiting));
    }

    public static IConnector hbaseConnectorInstance(String hosts, int maxWaiting) {
        HBaseConnector connector = new HBaseConnector();
        connector.setHosts(hosts);
        connector.setMaxWaitingTime(maxWaiting);

        return connector;
    }
}
