package org.sdi.example.clazz;

import java.sql.ResultSet;

/**
 *
 * a hive connector implementation of {@link IConnector}
 *
 * @author guoyanli
 */
public class HiveConnector implements IConnector {
    private String hosts;
    private Integer maxConnector;
    private Integer currentConnector = 0;


    public void tell() {
        System.out.println("this is hive connector " + hosts);
    }

    public ResultSet execute(String statement) {
        this.currentConnector += 1;
        System.out.println("hive running: \n" + statement);
        this.currentConnector -= 1;

        return null;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public Integer getMaxConnector() {
        return maxConnector;
    }

    public void setMaxConnector(Integer maxConnector) {
        this.maxConnector = maxConnector;
    }
}
