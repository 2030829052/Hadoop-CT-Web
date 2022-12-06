package org.fengyue.hadoopctweb.service;

import org.fengyue.hadoopctweb.entity.User;

public interface UserService {

    //增加用户的业务
    void addUser(User user);

    //根据用户名查找用户是否存在的业务
    User findByUsername(String username);

    //根据邮箱查找用户是否存在的业务
    User findByEmail(String email);

    User login(User user);
}
