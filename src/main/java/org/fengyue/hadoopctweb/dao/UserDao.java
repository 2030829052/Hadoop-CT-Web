package org.fengyue.hadoopctweb.dao;

import org.fengyue.hadoopctweb.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userMapper")
public interface UserDao {
    //增
    @Insert("insert into user(username,password,email)  values(#{username},#{password},#{email})")
    void add(User user);

    //删
    @Delete("delete from user where userId=#{userId}")
    int deleteById(int userId);

    //改
    @Update("update user set password=#{password} where username=#{username}")
    int update(User user);

    //查  查找所有用户
    @Select("select * from user")
    List<User> findAll();

    //查  根据用户名去查找用户
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    //查  根据邮箱去查找用户
    @Select("select * from user where email=#{email}")
    User findByEmail(String email);

    //根据账号跟密码查询用户
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(User user);
}
