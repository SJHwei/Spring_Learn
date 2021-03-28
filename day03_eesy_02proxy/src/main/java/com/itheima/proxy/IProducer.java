package com.itheima.proxy;

/**
 * @author ShiWei
 * @date 2021/3/28 - 21:05
 *
 * 对生产厂家要求的接口
 *
 * 在java中接口表示约定
 */
public interface IProducer {

    /**
     * 销售
     * @param money
     */
    public void saleProduct(float money);

    /**
     * 售后
     * @param money
     */
    public void afterService(float money);
}
