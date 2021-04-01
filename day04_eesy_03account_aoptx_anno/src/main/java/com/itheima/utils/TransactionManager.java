package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ShiWei
 * @date 2021/3/27 - 22:04
 *
 * 和事务管理相关的工具类，它包含了：开启事务，提交事务，回滚事务和释放连接。
 *
 * 写完下面四个方法之后透露一个细节：
 *     大家知道我们的连接都使用了连接池，连接池的好处是把消耗时间获取连接的这一部分放到我们的应用加载一开始，在web工程中当我们启动tomcate
 *     加载应用时，我们创建一些连接，从而在后续项目运行阶段不再跟数据库获取连接，保证了我们使用connection时的执行效率。
 *     那这个时候我们使用服务器，服务器也会有一个池的技术，叫做线程池，它的特点是当tomcat一起用时，会初始化一大堆的线程，放到一个容器中，接下来
 *     我们每次访问它都是从线程池中拿出一个线程，给我们使用。
 *     那如果是这样的话，线程池中的线程也跟我们连接池中的连接一样，调用close方法并不是真正的关闭，而是把它还回池中。
 *     当我们把连接关闭，线程还回池中时，线程上是有连接的，只不过这个连接已经被关闭了，当我们下次再获取这个线程，判断上面有没有连接时，得到的
 *     结果一定是有，但是这个连接已经不能用了，因为它已经被close过了。
 *     所以我们应该在整个线程用完之后，把这个线程和这个连接进行一个解绑的操作。现在是java工程，所以不涉及这个问题。当改为web应用开发时，就会涉及到这个问题。
 *     所以要解决这个问题，在connectionutils中写一个removeConnection方法，进行解绑。
 *
 *  接下来就是完善service，让其有事务的控制。
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1() {

    }

    /**
     * 开启事务
     */
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        try {
            connectionUtils.getThreadConnection().close(); //还回连接池中
            connectionUtils.removeConnection(); //进行解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            //1.获取参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue = pjp.proceed(args);
            //4.提交事务
            this.commit();

            //返回结果
            return rtValue;
        } catch (Throwable e) {
            //5.回滚事务
            this.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放资源
            this.release();
        }
    }
}
