package com.itheima.factory;

/**
 * @author ShiWei
 * @date 2021/3/17 - 19:51
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件。
 * javabean > 实体类
 *
 * 一个创建Bean对象的工厂
 *
 *     它就是创建文我们的service和dao对象的。
 *
 *     第一个：需要一个配置文件来配置我们的service和dao。配置的内容：唯一标识=全限定类名（key=value）
 *     第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *     我的配置文件可以是xml也可以是properties
 */
public class BeanFactory {
}
