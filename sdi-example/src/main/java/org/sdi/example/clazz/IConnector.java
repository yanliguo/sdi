package org.sdi.example.clazz;

import java.sql.ResultSet;

/**
 * base interface of db connector
 *
 * @author guoyanli
 */
public interface IConnector {
    /**
     * tell self description, output to std out {@link System.out}
     */
    void tell();

    /**
     *
     * execute statement to get result set {@link ResultSet}
     *
     * @param statement string of statements
     * @return result set of execution
     * @see ResultSet
     */
    ResultSet execute(String statement);
}
