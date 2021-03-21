package com.itheima.ui;

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
 *
 * 模拟一个表现层，用于调用业务层
 *
 * 这儿我们有两个可以称之为bean的，一个是service，一个是dao。
 */
public class Client {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象，ApplicationContext接口在构建核心容器时，创建对象采取的策略是采用立即加载的方式。单例对象适合。
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
//        IAccountService as = (IAccountService) ac.getBean("accountService");
//        as.saveAccount();

//        IAccountService as = (IAccountService) ac.getBean("accountService2");
//        as.saveAccount();

        IAccountService as = (IAccountService) ac.getBean("accountService3");
        as.saveAccount();
    }
}
