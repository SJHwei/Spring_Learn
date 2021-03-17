package com.itheima.ui;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * @author ShiWei
 * @date 2021/3/17 - 19:36
 *
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    public static void main(String[] args) {
        IAccountService as = new AccountServiceImpl();
        as.saveAccount();
    }
}