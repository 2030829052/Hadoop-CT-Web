package org.fengyue.hadoopctweb.service.impl;

import org.fengyue.hadoopctweb.dao.UserDao;
import org.fengyue.hadoopctweb.entity.User;
import org.fengyue.hadoopctweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void addUser(User user) {
        //实现我们的业务逻辑
        userDao.add(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

}
