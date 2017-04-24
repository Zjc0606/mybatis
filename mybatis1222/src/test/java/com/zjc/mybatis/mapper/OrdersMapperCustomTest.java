package com.zjc.mybatis.mapper;

import com.zjc.mybatis.po.Orders;
import com.zjc.mybatis.po.OrdersCustom;
import com.zjc.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by zjc on 2017/4/22.
 *
 */
public class OrdersMapperCustomTest {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws Exception {
        //mybatis配置文件
        String resources = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream= Resources.getResourceAsStream(resources);
        //创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //使用resultType实现一对一的查询（此方法比使用resultMap实现一对一查询更简单）
    @Test
    public void testFindOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        //调用mapper方法
        List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();
        sqlSession.close();

        System.out.println(list);
    }

    //使用resultMap实现一对一的查询
    @Test
    public void testFindOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        //调用mapper方法
        List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();
        sqlSession.close();

        System.out.println(list);
    }

    //使用resultMap实现一对多的查询
    @Test
    public void testFindOrdersAndOrderDetailResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        //调用mapper方法
        List<Orders> list = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
        sqlSession.close();

        System.out.println(list);
    }

    @Test
    public void testFindUserAndItemsResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        //调用mapper方法
        List<User> list = ordersMapperCustom.findUserAndItemsResultMap();
        sqlSession.close();

        System.out.println(list);
    }

    //查询订单关联查询用户，用户信息需要延迟加载
    @Test
    public void testFindOrdersUserLazyLoading() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        //调用mapper方法，查询订单信息(单表)
        List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();
        //遍历上边的订单列表
        for(Orders orders:list) {
            //执行getUser去查询用户信息,这里实现按需加载
            User user=orders.getUser();
            System.out.println(user);
        }
        sqlSession.close();

        System.out.println(list);
    }

    //一级缓存测试
    @Test
    public void testCache1() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();//创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //下边查询使用一个SqlSession
        //第一次发起请求，查询id为1的用户
        User user1 = userMapper.findUserById(1);
        System.out.println(user1);

//		如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

        //更新user1的信息
//        user1.setUsername("测试用户22");
//        userMapper.updateUser(user1);
        //执行commit操作去清空缓存
//        sqlSession.commit();

        //第二次发起请求，查询id为1的用户
        User user2 = userMapper.findUserById(1);
        System.out.println(user2);

        sqlSession.close();
    }

    // 二级缓存测试
    @Test
    public void testCache2() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        // 创建代理对象
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        // 第一次发起请求，查询id为1的用户
        User user1 = userMapper1.findUserById(1);
        System.out.println(user1);

        //这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
        sqlSession1.close();


/*
        //使用sqlSession3执行commit()操作
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        User user  = userMapper3.findUserById(1);
        user.setUsername("张明明");
        userMapper3.updateUser(user);
        //执行提交，清空UserMapper下边的二级缓存
        sqlSession3.commit();
        sqlSession3.close();
*/



        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        // 第二次发起请求，查询id为1的用户
        User user2 = userMapper2.findUserById(1);
        System.out.println(user2);

        sqlSession2.close();

    }

}