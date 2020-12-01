package com.iotend.tenant.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author huang
 */
@Slf4j
public enum DriverEnum {

    /**
     * mysql
     */
    MYSQL("com.mysql.cj.jdbc.Driver"),

    /**
     * oracle
     */
    ORACLE("oracle.jdbc.driver.OracleDriver"),

    /**
     * sql server
     */
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    ;

    /**
     * 驱动
     */
    private String driver;

    DriverEnum(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
