package com.jt.service;

import com.jt.pojo.User;


/**
 * @ClassName DubboUserService
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/21 11:26
 * @Version 1.0
 */
public interface DubboUserService {
    void saveUser(User user);
    String doLogin(User user);
}
