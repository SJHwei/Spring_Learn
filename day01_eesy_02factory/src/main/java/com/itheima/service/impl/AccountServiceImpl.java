package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

import java.sql.SQLOutput;

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
    private IAccountDao accountDao;

    //业务层也好，持久层也好，是很少包含可以修改的类的成员的，所以从这一点上出发，我们的对象可以是单例的，或者换句话来说，单例的对象对我们的效果最好
    //即很少有下面这行代码的写法，都是放在下面方法里面
    private int i = 1;

    public void saveAccount() {
        accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
