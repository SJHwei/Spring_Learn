package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountImpl;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.naming.Name;

/**
 * @author ShiWei
 * @date 2021/3/17 - 19:23
 *
 * 账户的业务层实现类
 *
 * 业务层调用持久层
 *
 * 曾经XML的配置：
 * <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl" scope="" init-method="" destroy-method="">
 *     <property name="" value="" | ref=""></property>
 * </bean>
 *
 * 根据曾经XML的配置中的三个细节以及spring中的依赖注入对注解进行分类：
 * （1）用于创建对象的：它们的作用就和在xml配置文件中编写一个<bean>标签实现的功能是一样的
 *      Component：
 *          作用：用于把当前类对象存入spring容器中
 *          属性：
 *              value：用于指定bean的id。当我们不写时，它的默认值是当前类名，且首字母小写。
 *      Controller：一般用在表现层；
 *      Service：一般用在业务层；
 *      Repostitory：一般用在持久层。
 *      以上三个注解他们的作用和属性与Comonent是一模一样的。它们三个是spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰。
 *
 * （2）用于注入数据的：它们的作用就和在xml配置文件中的bean标签中写一个<property>标签的作用是一样的
 *      Autowired：
 *          作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功。
 *                如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
 *                如果ioc容器中有多个类型匹配时，就要指定，例如：下面代码 accountDao1 = null; accountDao1.saveAccount();
 *          出现位置：可以出现在变量上，方法上。
 *          细节：在使用注解注入时，set方法就不是必须的了。
*       Qualifier：
 *          作用：在按照类中注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用。但是在给方法参数注入时可以。
 *          属性：
 *              value：用于指定注入bean的id。
 *          注意：不能单独使用，要和autowired一起使用。如果单独使用，会出现空指针异常，没有注进入。
 *      Resource：
 *          作用：直接按照bean的id注入。它可以独立使用。
 *          属性：
 *              name：用于指定bean的id。
 *      注意：以上三个注入都只能是注入其他bean类型的数据，而基本类型和string类型无法使用上述注解实现。
 *           另外，集合类型的注入只能通过xml来实现。
 *
 *      Value：
 *          作用：用于注入基本类型和string类型的数据
 *          属性：
 *              value：用于指定数据的值。它可以使用spring中的SpEL(也就是spring的el表达式)
 *                     SpEL的写法：${表达式}，鉴别el表达式时，看它出现在什么地方。
 *
 * （3）用于改变作用范围的：它们的作用就和在bean标签中使用scope属性实现的功能是一样的
 *      Scope：
 *          作用：用于指定bean的作用范围
 *          属性：
 *              value：指定范围的取值，常用取值：singleton，prototype。分别对应着单例和多例，如果不写，默认情况下也是单例的。
 *
 * （4）和声明周期相关：它们的作用就和在bean标签中使用init-method和destroy-method的作用是一样的。（了解）
 *      PreDestroy：
 *          作用：用于指定销毁方法
 *      PostConstruct：
 *          作用：用于指定初始化方法
 */
@Component("accountService")
//@Scope("prototype")
public class AccountServiceImpl implements IAccountService {

//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name="accountDao2")
    private IAccountDao accountDao = null;

    @PostConstruct
    public void init() {
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法执行了");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
