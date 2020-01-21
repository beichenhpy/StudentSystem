package com.hpy.student.system.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
   private static String url = null;
   private static String user = null;
   private static String pwd = null;
   private static QueryRunner queryRunner =null;

   private static ComboPooledDataSource pool = new ComboPooledDataSource();



    public static QueryRunner getQueryRunner(){
        synchronized (JdbcUtil.class){
            if (null == queryRunner){
                queryRunner = new QueryRunner();
            }
        }
        return queryRunner;
    }
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = pool.getConnection();
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
