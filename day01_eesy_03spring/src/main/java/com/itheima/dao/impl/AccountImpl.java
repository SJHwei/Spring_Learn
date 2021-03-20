package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;

/**
 * @author ShiWei
 * @date 2021/3/17 - 19:28
 *
 * 账户的持久层实现类
 */
public class AccountImpl implements IAccountDao {

    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
