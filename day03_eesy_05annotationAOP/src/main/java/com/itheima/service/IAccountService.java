package com.itheima.service;

/**
 * @author ShiWei
 * @date 2021/3/29 - 22:53
 *
 * 账户的业务层接口
 *
 * 为了表现三类方法，而并不局限这些方法干了什么事
 */
public interface IAccountService {

    /**
     * 模拟保存账户
     *
     * 无返回值无参类型
     */
    void saveAccount();

    /**
     * 模拟更新账户
     *
     * 无返回值有参类型
     */
    void updateAccount(int i);

    /**
     * 模拟删除账户
     *
     * 有返回值无参类型
     */
    int deleteAccount();

    //最后一个有返回值有参上面两个一组合就行了
}
