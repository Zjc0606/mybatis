package com.zjc.mybatis.po;

/**
 * Created by zjc on 2017/4/22.
 *订单的扩展类
 */
//通过此类映射订单和用户查询的结果，让此类集成字段较多的pojo类
public class OrdersCustom extends Orders{
    //添加用户属性
    /*
        USER.username
        USER.sex
        USER.address

     */
    private String username;
    private String sex;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
