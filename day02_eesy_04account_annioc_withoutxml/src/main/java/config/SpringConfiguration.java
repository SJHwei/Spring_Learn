package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author ShiWei
 * @date 2021/3/25 - 22:49
 *
 * 该类是一个配置类，它的作用和bean.xml是一样的
 *
 * spring中新的注解：
 *     （1）Configuration：
 *             作用：指定当前类是一个配置类
 *     （2）ComponentScan：
 *             作用：用于通过注解指定spring在创建容器时要扫描的包，扫描包时，会扫描这个包下所有的类，首先它必须得认为这个类是一个配置类，才会对里面的注解进行扫描。
 *             属性：
 *                 value：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包。注意：必须是类路径。
 *                        我们使用此注解就等同于在xml中配置了：
 *                        <context:component-scan base-package="com.itheima"></context:component-scan>
 *     （3）Bean：
 *             作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 *             属性：
 *                 name：用于指定bean的id。当不写时，默认值是当前方法的名称。
 *             细节：
 *                 当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象。
 *                 查找的方式和Autowired注解的作用是一样的。
 *     （4）Import：
 *             作用：用于导入其他的配置类，注意细节2中的两种方法就都可以不写了。
 *             属性：
 *                 value：用于指定其他配置类的字节码。
 *                        当我们使用import的注解之后，有import注解的类就是父配置类，而导入的都是子配置类。
 *     （5）PropertySource：
 *             作用：用于指定properties文件的位置
 *             属性：
 *                 value：指定文件的名称和路径。
 *                        关键字：classpath：表示类路径下。
 *
 * 有几个细节需要注意：
 *     （1）曾经我们的QueryRunner是多例的，而现在它肯定是个单例的，所以需要加一个注解@Scope("prototype")，将其变为多例的。
 *     （2）当配置类作为AnnotationConfigApplicationContext对象创建的参数时，@Configuration注解可以不写。
 *          总结：要么不写注解Configuration，在AnnotationConfigApplicationContext的构造方法参数中写上配置类类名；
 *               要么写上注解Configuration，并且配置扫描的包，让spring把这个类扫了。
 *     （3）AnnotationConfigApplicationContext构造函数的参数中的字节码是并列关系，而使用注解import则是父子关系。显示父子关系的配置更清晰合理一些。
 */
@Configuration
@ComponentScan("com.itheima")
@Import(JdbcConfig.class) //import实现了把其他的子配置类都导入到主配置类中，从而只用加载主配置类就可以把细小的自配置类全部加载进来。
@PropertySource("classpath:jdbcConfig.properties")
//主配置类
public class SpringConfiguration {

}
