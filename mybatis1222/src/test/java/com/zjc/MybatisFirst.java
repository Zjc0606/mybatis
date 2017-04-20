package com.zjc;

import com.zjc.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by zjc on 2017/4/18.
 *
 */
public class MybatisFirst {
    //根据id查询用户信息，得到一条记录的结果
    @Test
    public void findUserByIdTset() {
        //mybatis配置文件
        String resources = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream= null;
        SqlSession sqlSession=null;
        try {
            inputStream = Resources.getResourceAsStream(resources);
            //创建会话工厂，传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            sqlSession= sqlSessionFactory.openSession();
            //通过SqlSession操作数据库
            //第一个参数：映射文件中statement的id，等于namespace+statement的id
            //第二个参数：指定和映射文件中所匹配的parameterType的参数
            //sqlSession.selectOne结果是与映射文件中所匹配的resultType类型的对象
            User user=sqlSession.selectOne("test.findUserById",1);
            System.out.println("user:"+user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (sqlSession!=null){
                sqlSession.close();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据用户名称模糊查询用户列表
    @Test
    public void findUserByNameTest() {
        //mybatis配置文件
        String resources = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream= null;
        SqlSession sqlSession=null;
        try {
            inputStream = Resources.getResourceAsStream(resources);
            //创建会话工厂，传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            sqlSession= sqlSessionFactory.openSession();
            //list中的user和映射文件中resultType所指定的类型一致
            List<User> list=sqlSession.selectList("test.findUserByName", "小明");
            System.out.println("userlist:"+list);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (sqlSession!=null){
                sqlSession.close();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //添加用户信息
    @Test
    public void insertUserTest() {
        //mybatis配置文件
        String resources = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream= null;
        SqlSession sqlSession=null;
        try {
            inputStream = Resources.getResourceAsStream(resources);
            //创建会话工厂，传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            sqlSession= sqlSessionFactory.openSession();
            //插入用户对象
            User user=new User();
            user.setUsername("王小军");
            user.setBirthday(new Date());
            user.setSex("1");
            user.setAddress("河南郑州");
            //list中的user和映射文件中resultType所指定的类型一致
            sqlSession.insert("test.insertUser", user);
            //提交事务
            sqlSession.commit();
            System.out.println("id="+user.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (sqlSession!=null){
                sqlSession.close();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //删除用户信息
    @Test
    public void deletetUserTest() {
        //mybatis配置文件
        String resources = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream= null;
        SqlSession sqlSession=null;
        try {
            inputStream = Resources.getResourceAsStream(resources);
            //创建会话工厂，传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            sqlSession= sqlSessionFactory.openSession();
            //传入id删除用户
            sqlSession.delete("test.deleteUser", 31);
            //提交事务
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (sqlSession!=null){
                sqlSession.close();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //更新用户信息
    @Test
    public void updateUserTest() {
        //mybatis配置文件
        String resources = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream= null;
        SqlSession sqlSession=null;
        try {
            inputStream = Resources.getResourceAsStream(resources);
            //创建会话工厂，传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            sqlSession= sqlSessionFactory.openSession();
            //更新用户信息
            User user=new User();
            user.setId(28);
            user.setUsername("王大军");
            user.setBirthday(new Date());
            user.setSex("2");
            user.setAddress("河南郑州");
            //list中的user和映射文件中resultType所指定的类型一致
            sqlSession.update("test.updateUser", user);
            //提交事务
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (sqlSession!=null){
                sqlSession.close();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
