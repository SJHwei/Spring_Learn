package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ShiWei
 * @date 2021/3/15 - 22:54
 *
 * 程序的耦合:
 *     （1）耦合：程序间的依赖关系。
 *     （2）包括：类之间的依赖和方法间的依赖。
 *     （3）以下程序耦合的表现：这个类中如果没有mysql驱动（new com.mysql.jdbc.Driver()），就无法编译。（在编译期，就具体的依赖于某个类或某个jar包）
 *
 * 解耦：
 *     （1）降低程序间的依赖关系。
 *     （2）实际开发中应该做到：编译期不依赖，运行时才依赖。
 *
 * 类之间依赖解耦的思路：
 *     （1）第一步：使用反射机制来创建对象，而避免使用new关键字。
 *     （2）第二步：通过读取配置文件来获取要创建的对象全限定类名。
 */
public class JdbcDemo1 {

    public static void main(String[] args) throws Exception {
        //1.注册驱动
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy", "root", "1109");
        //3.获取操作数据库的预处理对象
        PreparedStatement pstm = conn.prepareStatement("select * from account");
        //4.执行SQL，得到结果集
        ResultSet rs = pstm.executeQuery();
        //5.遍历结果集
        while (rs.next()){
            System.out.println(rs.getString("name"));
        }
        //6.释放资源
        rs.close();
        pstm.close();
        conn.close();
    }
}
