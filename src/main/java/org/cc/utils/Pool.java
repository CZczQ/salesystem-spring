package org.cc.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author cc
 * @Date 2022/10/12 19:29
 * @PackageName:org.cc.utils
 * @ClassName: pool
 * @Description: TODO
 * @Version 1.0
 */
public class Pool {
    private static DruidDataSource ds; //连接池对象
    static {
        Properties properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream("D:\\Code\\Maven\\salesystem-spring\\src\\main\\resources\\db.properties");

            properties.load(fis);
            ds = new DruidDataSource();
            ds.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
            ds.setUrl(properties.getProperty("jdbc.url"));
            ds.setUsername(properties.getProperty("jdbc.username"));
            ds.setPassword(properties.getProperty("jdbc.password"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
