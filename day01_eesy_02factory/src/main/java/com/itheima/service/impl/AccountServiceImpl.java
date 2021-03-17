package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * @author ShiWei
 * @date 2021/3/17 - 19:23
 *
 * 账户的业务层实现类
 *
 * 业务层调用持久层
 */
public class AccountServiceImpl implements IAccountService {

//    private IAccountDao accountDao = new AccountImpl();
    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
