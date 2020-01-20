package com.hpy.student.system.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
   private static String url = null;
   private static String user = null;
   private static String pwd = null;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./src/db.properties"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            pwd = properties.getProperty("pwd");
            String driver = properties.getProperty("driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void close(Connection connection){
        close(connection,null,null);
    }
    public static void close(Connection connection, Statement statement){
       close(connection, statement,null);
    }
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if(null != connection){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != statement){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (null != resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
