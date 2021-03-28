package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ShiWei
 * @date 2021/3/27 - 21:45
 *
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定。
 * 即使用ThreadLocal对象把Connection和当前线程绑定，从而使一个线程中只有一个能控制事务的对象。
 *
 *
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     *
     * 写完这个方法就实现了当前线程上有这么一个连接了，接下来再往后，我们需要做的事情就变得更重要了，
     * 因为大家都知道控制事务是靠connection的首先把手动提交改成自动提交，然后再通过commit和rollback方法来对事务进行提交。
     * 接下来我们写一下关于事务操作的工具类。
     */
    public Connection getThreadConnection() {
        try {
            //1.先从ThreadLocal上获取
            Connection conn = t1.get();
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        t1.remove();
    }
}
