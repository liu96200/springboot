package com.luopeng.demo.dao;

import com.luopeng.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    //查询用户通过密码和姓名
     public User getByPasswordAndUsername(String username,String password);

}
