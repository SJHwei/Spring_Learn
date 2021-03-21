package com.itheima.service.impl;

import com.itheima.service.IAccountService;

import java.util.Date;

/**
 * @author ShiWei
 * @date 2021/3/17 - 19:23
 * <p>
 * 账户的业务层实现类
 * <p>
 * 业务层调用持久层
 */
public class AccountServiceImpl implements IAccountService {

    //如果是经常变化的数据，并不适用于注入的方式
    //把重点关注在数据类型上
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了。。。" + name + "," + age + "," + birthday);
    }

}
