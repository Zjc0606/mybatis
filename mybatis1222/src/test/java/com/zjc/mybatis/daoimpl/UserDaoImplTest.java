package com.zjc.mybatis.daoimpl;

import com.zjc.mybatis.dao.UserDao;
import com.zjc.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by zjc on 2017/4/20.
 *
 */
public class UserDaoImplTest {
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

    @Test
    public void testFindUserById() throws Exception {
        //创建UserDao的对象
        UserDao userDao=new UserDaoImpl(sqlSessionFactory);

        //调用UserDao的方法
        User user = userDao.findUserById(1);
        System.out.println(user);
    }
}