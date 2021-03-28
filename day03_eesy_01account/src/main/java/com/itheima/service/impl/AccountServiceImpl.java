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
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return accounts;
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            //注意：throw之后，程序不再执行，作用类似于return，所以这里不能没有throw
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            txManager.release();
        }
    }

    public Account findAccountById(Integer accountId) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            Account account = accountDao.findAccountById(accountId);
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return account;
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            txManager.release();
        }
    }

    public void saveAccount(Account account) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            txManager.commit();
        } catch (Exception e) {
            //4.回滚操作
            txManager.rollback();
        } finally {
            //5.释放连接
            txManager.release();
        }

    }

    public void updateAccount(Account account) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            txManager.commit();
        } catch (Exception e) {
            //4.回滚操作
            txManager.rollback();
        } finally {
            //5.释放连接
            txManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.deleteAccount(accountId);
            //3.提交事务
            txManager.commit();
        } catch (Exception e) {
            //4.回滚操作
            txManager.rollback();
        } finally {
            //5.释放连接
            txManager.release();
        }

    }

    public void transfer(String sourceName, String targetName, Float money) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作

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

            int i = 1/0;

            //2.6 更新转入账户
            accountDao.updateAccount(target);

            //3.提交事务
            txManager.commit();
        } catch (Exception e) {
            //4.回滚操作
            txManager.rollback();
            e.printStackTrace();
        } finally {
            //5.释放连接
            txManager.release();
        }

    }

}
