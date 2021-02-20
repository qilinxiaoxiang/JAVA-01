package com.wsbo.week5.database;

import java.sql.*;

/**
 * @author 项峥
 */
public class Jdbc {
    public static void main(String[] args) throws SQLException {
        //链接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        //创建执行声明
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
