package org.sdi.example.clazz;

/**
 * Created by guoyanli on 12/30/15.
 */
public class AbstractCoupledDao implements IDao {
    protected IConnector connector;

    public AbstractCoupledDao() {}

    public AbstractCoupledDao(IConnector connector) {
        this.connector = connector;
    }

    public IConnector getConnector() {
        return connector;
    }

    public void setConnector(IConnector connector) {
        this.connector = connector;
    }
}
