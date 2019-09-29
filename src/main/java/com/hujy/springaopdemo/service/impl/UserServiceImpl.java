package com.hujy.springaopdemo.service.impl;

import com.hujy.springaopdemo.annotation.Log;
import com.hujy.springaopdemo.pojo.User;
import com.hujy.springaopdemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 业务实现
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-29 10:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Log("print")
    @Override
    public User getUser(String userCode) {
        if (userCode == null) {
            throw new IllegalArgumentException("入参为空");
        }
        User u = new User();
        u.setUserCode(userCode);
        u.setUserName("James");
        u.setAge(30);
        System.out.println("【业务方法】getUser:" + u);
        return u;
    }

    @Override
    public String saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("入参为空");
        }
        System.out.println("【业务方法】save:" + user);
        return "ok";
    }
}
