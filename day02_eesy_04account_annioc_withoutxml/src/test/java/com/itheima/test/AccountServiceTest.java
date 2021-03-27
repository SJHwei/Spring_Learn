package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import config.JdbcConfig;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author ShiWei
 * @date 2021/3/25 - 21:27
 *
 * 使用Junit单元测试：测试我们的配置
 *
 * 解决代码重复的问题
 *
 * 站在测试员的角度进行测试
 *
 * spring整合junit的配置
 *     1.导入spring整合junit的jar(坐标)；
 *     2.使用junit提供的一个注解把原有的main方法替换了，替换成spring提供的。使用@RunWith注解实现；
 *     3.告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置。使用@ContextConfiguration注解实现
 *         Locations：指定xml文件的位置，加上classpath关键字，表示类路径下
 *         classes：指定注解类所在的位置
 *
 * 注意：当我们使用spring 5.x版本的时候，要求junit的jar必须是4.12及以上。
 * 注意：当版本不对时，我们执行方法报错时不会说明是版本问题，但是执行类时会说明版本问题。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfiguration.class)
public class AccountServiceTest {
//    private ApplicationContext ac;

    @Autowired
    private IAccountService as;

//    @Before
//    public void  init() {
//        //1.获取容器
//        //注意：如果也不想在配置类JdbcConfig上写注解@Configuration，那么这里的参数就必须加上JdbcConfig.class
//        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        //2.得到业务层对象
//        as = ac.getBean("accountService", IAccountService.class);
//    }

    @Test
    public void testFindAll() {
//        //1.获取容器
//        //注意：如果也不想在配置类JdbcConfig上写注解@Configuration，那么这里的参数就必须加上JdbcConfig.class
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        //2.得到业务层对象
//        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3.执行方法
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
//        //1.获取容器
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        //2.得到业务层对象
//        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3.执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test anno");
        account.setMoney(12345f);
//        //1.获取容器
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        //2.得到业务层对象
//        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3.执行方法
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
//        //1.获取容器
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        //2.得到业务层对象
//        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3.执行方法
        Account account = as.findAccountById(4);
        account.setMoney(23456f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
//        //1.获取容器
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        //2.得到业务层对象
//        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3.执行方法
        as.deleteAccount(4);
    }
}
