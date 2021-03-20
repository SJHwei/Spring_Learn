package com.itheima.factory;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * @author ShiWei
 * @date 2021/3/20 - 20:31
 *
 * 模拟一个工厂类（该类可能是存在于jar包中的，我们无法通过修改源码的方式来提供默认构造函数）
 *
 * 注意：StaticFactory和InstanceFactory都要看成是jar包中的类，所以都是改不了的。
 */
public class StaticFactory {

    public static IAccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
