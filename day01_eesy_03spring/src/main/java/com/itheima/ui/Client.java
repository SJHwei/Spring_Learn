package com.itheima.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author ShiWei
 * @date 2021/3/17 - 19:36
 * <p>
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     * 获取spring的IOC核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *     （1）ClassPathXmlApplicationContext：他可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了。（更常用）
     *     （2）FileSystemXmlApplicationContext：他可以加载磁盘任意路径下的配置文件(必须有访问权限)。
     *     （3）AnnotationConfigApplicationContext：它是用于读取注解创建容器的，是后面的内容。
     *
     * 核心容器的两个接口引发的问题：
     *     （1）ApplicationContext：单例对象适用 【采用此接口】
     *         它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上使用反射的方式创建配置文件中配置的对象。
     *     （2）BeanFactory：多例对象适用
     *         它在构建核心容器时，创建对象采用的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        ApplicationContext ac = new FileSystemXmlApplicationContext("C:\\Users\\SJHwei\\Desktop\\bean.xml");

//        2.根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);

        System.out.println(as);
        System.out.println(adao);
//        as.saveAccount();

        //--------------BeanFactory--------------------
//        Resource resource = new ClassPathResource("bean.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
//        IAccountService as = (IAccountService) factory.getBean("accountService");
//        IAccountDao adao = factory.getBean("accountDao", IAccountDao.class);
//        System.out.println(as); //此处才会创建对象，因为这儿才用对象
//        System.out.println(adao); //此处才会创建对象，因为这儿才用对象

    }
}
