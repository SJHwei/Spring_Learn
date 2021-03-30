package com.itheima.domain;

import java.io.Serializable;

/**
 * @author ShiWei
 * @date 2021/3/30 - 22:08
 *
 * 账户的实体类
 *
 * 首先在数据库中创建一张account表，并且创建这张表的实体类
 */
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Float money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
