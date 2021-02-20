package com.wsbo.week5.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 项峥
 */
public class Hikari {
    public static void main(String[] args) throws SQLException {
        HikariConfig conf = new HikariConfig();
        conf.setUsername("root");
        conf.setPassword("root");
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        HikariDataSource ds = new HikariDataSource(conf);
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        //执行Sql语句
        ResultSet rs = stmt.executeQuery("select user_id,user_name from user");
        //处理结果集
        while (rs.next()) {
            System.out.println("用户ID" + rs.getInt(1) + " 用户名：" + rs.getString(2));
        }
        //释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
