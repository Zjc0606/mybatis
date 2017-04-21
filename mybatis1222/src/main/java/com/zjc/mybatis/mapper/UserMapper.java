package com.zjc.mybatis.mapper;

import com.zjc.mybatis.po.User;
import com.zjc.mybatis.po.UserCustom;
import com.zjc.mybatis.po.UserQueryVo;

import java.util.List;

/**
 * Created by zjc on 2017/4/19.
 *
 */
public interface UserMapper {
    //用户信息综合查询
    public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

    //用户信息综合查询总数
    public int findUserCount(UserQueryVo userQueryVo) throws Exception;

    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据id查询用户信息，使用resultMap输出
    public User findUserByIdResultMap(int id) throws Exception;

    //根据用户名查询用户信息
    public List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    public void insertUser(User user) throws Exception;

    //删除用户信息
    public void deleteUser(int id) throws Exception;
}
