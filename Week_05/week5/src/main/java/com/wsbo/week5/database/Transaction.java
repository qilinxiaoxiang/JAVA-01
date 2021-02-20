package com.wsbo.week5.database;

import java.sql.*;

/**
 * @author 项峥
 */
public class Transaction {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

        //取消默认事务
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement("update user set user_name=? where user_id = ?");
        pstmt.setString(1, "xiangzheng");
        pstmt.setInt(2, 1);
        pstmt.execute();

        pstmt = conn.prepareStatement("select user_name from user where user_id=?");
        pstmt.setInt(1, 1);
        ResultSet rs = pstmt.executeQuery();
        //提交事务
        conn.commit();

        if (rs.next()) {
            System.out.println("用户名：" + rs.getString(1));
        }
        rs.close();
        pstmt.close();
        conn.close();
    }
}
