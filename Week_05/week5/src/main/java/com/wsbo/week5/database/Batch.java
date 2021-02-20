package com.wsbo.week5.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 项峥
 */
public class Batch {
    public static void main(String[] args) throws SQLException {
        //链接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        //创建执行声明
        Statement stmt = conn.createStatement();
        stmt.addBatch("insert into user(user_id,user_name) values(1,'xiangzheng1')");
        stmt.addBatch("insert into user(user_id,user_name) values(2,'xiangzheng2')");
        stmt.addBatch("insert into user(user_id,user_name) values(3,'xiangzheng3')");
        //执行批处理语句
        stmt.executeBatch();
        //释放资源
        stmt.close();
        conn.close();
    }
}
