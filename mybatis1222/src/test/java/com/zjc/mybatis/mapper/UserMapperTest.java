package com.zjc.mybatis.mapper;

import com.zjc.mybatis.po.User;
import com.zjc.mybatis.po.UserCustom;
import com.zjc.mybatis.po.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjc on 2017/4/20.
 *
 */
public class UserMapperTest {
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
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建UserMapper对象,mybaitis自动生成mapper大力对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //调用userMapper的方法
        User user = userMapper.findUserById(1);
        sqlSession.close();
        System.out.println(user);
    }
    @Test
    public void testFindUserByName() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建UserMapper对象,mybaitis自动生成mapper大力对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //调用userMapper的方法
        List<User> list = userMapper.findUserByName("小明");
        sqlSession.close();
        System.out.println(list);
    }

    //用户信息综合查询
    @Test
    public void testFindUserList() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建UserMapper对象,mybaitis自动生成mapper大力对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //创建包装对象，设置查询条件
        UserQueryVo userQueryVo=new UserQueryVo();
        UserCustom userCustom=new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("小明");
        //传入多个id
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(10);
        ids.add(16);
        userQueryVo.setIds(ids);
        userQueryVo.setUserCustom(userCustom);

        //调用userMapper的方法
        List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
        System.out.println(list);
    }

    //用户信息综合查询总数
    @Test
    public void testFindUserCount() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建UserMapper对象,mybaitis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //创建包装对象，设置查询条件
        UserQueryVo userQueryVo=new UserQueryVo();
        UserCustom userCustom=new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("张三丰");
        userQueryVo.setUserCustom(userCustom);

        //调用userMapper的方法
        int count= userMapper.findUserCount(userQueryVo);
        sqlSession.close();
        System.out.println("count="+count);
    }

    //根据id查询用户信息，使用resultMap输出
    @Test
    public void testfindUserByIdResultMap() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建UserMapper对象,mybaitis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


        //调用userMapper的方法
        User user=userMapper.findUserByIdResultMap(1);
        sqlSession.close();
        System.out.println(user);
    }
}