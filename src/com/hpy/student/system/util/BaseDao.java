package com.hpy.student.system.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于元数据，BeanUtils还有反射思想完成的一个Query和Update方法
 * Update方法用于处理针对于数据库修改的方法，insert update delete create drop
 * Query方法用于处理查询语句 select
 */

public class BaseDao {
    /*
    完成一个统一的Update方法
    方法分析：
        方法名:
            update
        形式参数列表：
            String sql 当前方法指定的SQL语句
            需要SQL语句对应的参数
                a. 参数个数不确定
                b. 参数类型不确定
                ...不定长参数 底层是一个 Object类型数组
                Object[] parameters
                List<Object> parameters
         返回值类型
            void
            int 返回当前 SQL语句执行数据库受影响的行数
         权限修饰：
            public
         public int update(String sql, Object[] parameters)
     */

    /**
     * 统一的修改方法，用于处理 insert，update，delete，create，drop等SQL语句
     *
     * @param sql        需要执行的 SQL语句
     * @param parameters 对应 SQL 语句的参数数组，为 Object类型
     * @return 返回当前SQL语句执行 数据库受影响行数 rows affected
     * @throws SQLException SQL语句为 null
     */
    public int update(String sql, Object[] parameters) throws SQLException {
        if (null == sql) {
            throw new SQLException("SQL is null");
        }

        // 1. 获取数据库连接
        Connection connection = JdbcUtil.getConnection();

        // 2. 使用SQL语句 获取对应的PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 处理PreparedStatement参数
        preparedStatement = parametersAssignment(preparedStatement, parameters);

        // 4. 执行SQL语句
        int i = preparedStatement.executeUpdate();

        // 5. 关闭资源
        JdbcUtil.close(connection, preparedStatement);

        // 6. 返回受影响行数
        return i;
    }

    /*
    完成一个统一的查询方法
        1. 查询一个数据
        2. 查询多个数据
        3. 查询任意类对象类型的数据

    方法分析:
        方法名：
            query
        形式参数列表:
            String SQL 需要执行的 SQL语句
            Object[] parameters 对应 SQL语句的一个参数数组
            ???
            Class<T> cls
                1. 第三个参数需要带有泛型，用于约束当前方法中使用的泛型
                2. 这个参数还需要提供一个非常重要的数据！！！指定查询数据的 Class类对象，通过 Class类对象
                借助于反射，就可以为所欲为！！！
         返回值类型：
            数组 Pass 不要
            List<T> 这里需要使用泛型，而不是直接使用 Object，我们不希望遇到过多的数据类型转换，避免
            因为数据类型一致化问题导致代码存在隐患
         权限修饰：
            public
         带有自定义泛型：
            <T>

         public <T> List<T> query(String sql, Object[] parameters, Class<T> cls)
     */

    /**
     * 通用的查询数据库的方法，可以查询指定的任意类对象类型
     *
     * @param sql        指定的SQL语句，这里需要的是 select语句 DQL语句
     * @param parameters 对应 SQL语句的参数数组
     * @param cls        当前查询指定类对象类型的 Class类对象
     * @param <T>        当前方法中使用的泛型
     * @return 返回包含指定类对象类型的 List集合，没有任何有效元素，返回 null
     * @throws SQLException SQL异常
     */
    public <T> List<T> query(String sql, Object[] parameters, Class<T> cls)
            throws SQLException {
        if (null == sql || null == cls) {
            throw new NullPointerException();
        }

        // 1. 获取数据库连接对象
        Connection connection = JdbcUtil.getConnection();

        // 2. 使用 SQL预处理，获取 PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 处理PreparedStatement参数
        preparedStatement = parametersAssignment(preparedStatement, parameters);

        // 5. 执行 SQL语句，获取 ResultSet 结果集对象
        ResultSet resultSet = preparedStatement.executeQuery();

        // 6. 准备 List 集合
        List<T> list = new ArrayList<>();

        // 7. 获取结果集元数据
        ResultSetMetaData metaData = resultSet.getMetaData();

        // 8. 获取结果集元数据中的字段个数
        int columnCount = metaData.getColumnCount();

        // 9. 遍历结果集
        while (resultSet.next()) {
            T t = null;
            try {
                // 10. 创建对应数据类型的类对象，使用反射处理
                t = cls.getConstructor(null).newInstance(null);
                // 11. 利用结果集元数据中的字段个数，来处理数据行
                for (int i = 1; i <= columnCount; i++) {
                    // 12. 获取字段名
                    String fieldName = metaData.getColumnName(i);

                    // 13. 获取对应字段名的数据
                    Object value = resultSet.getObject(fieldName);

                    // 14. 使用BeanUtils给指定字段的数据在类对象中赋值
                    BeanUtils.setProperty(t, fieldName, value);
                }
                // 15. 存放到 List集合中
                list.add(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        }

        JdbcUtil.close(connection, preparedStatement, resultSet);

        // 15. 如果 List集合中的有效元素个数为 0 ，返回 null，其他返回 List对象
        return list.size() != 0 ? list : null;
    }

    /*
    权限修饰:
        private 仅供类内使用，在类内处理当前方法中 PreparedStatement
    方法名：
        参数处理 parametersAssignment
    形式参数列表:
        PreparedStatement类对象
        参数数组 Object[] parameters
    返回值类型：
        PreparedStatement对象

    private PreparedStatement parametersAssignment(PreparedStatement preparedStatement, Object[] parameters)
     */

    /**
     * 类内处理PreparedStatement对象参数的方法
     *
     * @param preparedStatement PreparedStatement 对象
     * @param parameters        参数数组
     * @return PreparedStatement对象
     */
    private PreparedStatement parametersAssignment(PreparedStatement preparedStatement, Object[] parameters) throws SQLException {
        // 3. 获取 SQL语句的参数个数
        int parameterCount = preparedStatement.getParameterMetaData().getParameterCount();

        // 4. 给予 PreparedStatement 预处理 SQL语句赋值参数
        if (parameters != null && parameters.length == parameterCount) {
            for (int i = 1; i <= parameterCount; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
        }

        return preparedStatement;
    }
}

