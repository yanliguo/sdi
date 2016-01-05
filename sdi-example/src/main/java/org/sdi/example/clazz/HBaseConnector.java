package org.sdi.example.clazz;

import java.sql.ResultSet;

/**
 *
 * hbase connection of {@link IConnector}
 *
 * @author guoyanli
 */
public class HBaseConnector implements IConnector {
    private String hosts;
    private Integer maxWaitingTime;


    public void tell() {
        System.out.println("this is hbase connector " + hosts);
    }

    public ResultSet execute(String statement) {
        try {
            Long currentMillis = System.currentTimeMillis();

            System.out.println("hbase connector connected @" + currentMillis);
            System.out.println("running: \n" + statement);
            Thread.sleep(200);
            System.out.println("Time used: " + (System.currentTimeMillis() - currentMillis));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public Integer getMaxWaitingTime() {
        return maxWaitingTime;
    }

    public void setMaxWaitingTime(Integer maxWaitingTime) {
        this.maxWaitingTime = maxWaitingTime;
    }
}
