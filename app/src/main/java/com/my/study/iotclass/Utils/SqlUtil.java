package com.my.study.iotclass.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlUtil {
    private static Connection connection = null;

    public static Connection openConnection(String url, String user, String password) {
        try {
            final String DRIVER_NAME = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }

        return connection;
    }
}
