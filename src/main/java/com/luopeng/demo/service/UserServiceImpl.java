package com.luopeng.demo.service;

import com.luopeng.demo.dao.UserDao;
import com.luopeng.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl{
    @Autowired
    UserDao userDao;

    public List<User> getAll(){

        return userDao.findAll();
    }
    public User getUser(String username, String password) {
        return userDao.getByPasswordAndUsername(username,password);

    }
    public boolean UserJudge(User user){
        String username=user.getUsername();
        List<User> list = userDao.findAll();
        List<Object> list1 = new ArrayList<>();
        for (User user1 : list) {
            list1.add(user1.getUsername());
        }
        if (list1.contains(username))
            return true;//返回true说明此用户已注册
        return false;
    }

    public void insertUser(User user) {
        //userDao.save(user);
        userDao.saveAndFlush(user);//不会保存到缓存中，立即刷新到DB
    }
}
