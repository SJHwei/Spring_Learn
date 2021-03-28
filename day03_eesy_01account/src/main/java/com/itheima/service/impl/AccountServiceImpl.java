package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

/**
 * @author ShiWei
 * @date 2021/3/25 - 19:21
 *
 * 账户的业务层实现类
 *
 * 事务控制应该都是在业务层，昨天讲解(spring基于注解的ioc及如何使用)的时候事务全都是在持久层，而今天的知识点是AOP的概念
 *
 * 在dbutils中讲过，如果我的dao中在执行方法的同时给QueryRunner注入了connection之后，它就会从连接里面取一个，而现在显然是不希望它从连接里面取，
 * 于是，就不要在bean.xml的配置QueryRunner中再给他注入connection，但是当我们不提供connection对象的时候，我们就发现我们的dao中的操作就没有connection，
 * 那此时应该怎么办呢？
 * 很简单，我们需要在AccountDaoImpl类中加一个新的对象ConnectionUtils。
 *
 * 这些做完之后，代码回归正常了：
 *     （1）事务控制又由持久层回到了我们的业务层。
 *
 * 但是AccountServiceImpl_OLD存在问题：
 *     （1）重复代码；
 *     （2）存在方法间依赖。
 *
 * 解决办法(如何能够让AccountServiceImpl中的方法能够正常执行，同时还有事务的支持)：动态代理。
 *
 * 现在事务都已经由代理对象(BeanFactory)来控制了，那这个时候我们这个事务控制和业务层的方法进行了真正的分离。
 *
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();

    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);

    }

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer...");
        //2.1 根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2 根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3 转出账户减钱
        source.setMoney(source.getMoney() - money);
        //2.4 转入账户加钱
        target.setMoney(target.getMoney() + money);
        //2.5 更新转出账户
        accountDao.updateAccount(source);

//        int i = 1/0;

        //2.6 更新转入账户
        accountDao.updateAccount(target);
    }

}
