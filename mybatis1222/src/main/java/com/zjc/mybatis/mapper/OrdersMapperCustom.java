package com.zjc.mybatis.mapper;

import com.zjc.mybatis.po.Orders;
import com.zjc.mybatis.po.OrdersCustom;
import com.zjc.mybatis.po.User;

import java.util.List;

/**
 * Created by zjc on 2017/4/22.
 * 订单的mapper
 */
public interface OrdersMapperCustom {
    //查询订单关联查询用户
    public List<OrdersCustom> findOrdersUser() throws Exception;

    //查询订单关联查询用户,使用resultMap
    public List<Orders> findOrdersUserResultMap() throws Exception;

    //查询订单(关联用户）及订单明细,使用resultMap
    public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;

    //查询用户购买商品信息
    public List<User> findUserAndItemsResultMap() throws Exception;

    //查询订单关联查询用户，用户信息需要延迟加载
    public List<Orders> findOrdersUserLazyLoading() throws Exception;
}
