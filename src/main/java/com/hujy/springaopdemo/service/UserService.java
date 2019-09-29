package com.hujy.springaopdemo.service;

import com.hujy.springaopdemo.pojo.User;

/**
 * 业务接口
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-29 10:47
 */
public interface UserService {

    User getUser(String userCode);

    String saveUser(User user);

}
