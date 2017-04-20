package com.zjc.mybatis.daoimpl;

import com.zjc.mybatis.dao.UserDao;
import com.zjc.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by zjc on 2017/4/19.
 *
 */
public class UserDaoImpl implements UserDao{
    //需要向dao实现类中注入SqlSessionFactory
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public User findUserById(int id) throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserById", id);
        sqlSession.close();
        return user;
    }

    public List<User> findUserByName(String name) throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<User> list= sqlSession.selectList("test.findUserByName", name);
        sqlSession.close();
        return list;
    }


    public void insertUser(User user) throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        sqlSession.insert("test.insertUser", user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    public void deleteUser(int id) throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        sqlSession.insert("test.deleteUser", id);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
