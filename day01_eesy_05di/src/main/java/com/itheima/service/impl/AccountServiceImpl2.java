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
public class AccountServiceImpl2 implements IAccountService {

    //如果是经常变化的数据，并不适用于注入的方式
    //把重点关注在数据类型上
    private String name;
    private Integer age;
    private Date birthday;

    public void setUserName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了。。。" + name + "," + age + "," + birthday);
    }

}
