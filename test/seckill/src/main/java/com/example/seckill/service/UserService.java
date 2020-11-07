package com.example.seckill.service;

import com.example.seckill.service.model.UserModel;
import org.apache.catalina.User;

/**
 * 用户实现类接口
 */
public interface UserService {

    // 获取用户信息
    UserModel getUser(Integer id);
    // 创建用户
    void register(UserModel userModel) throws Exception;
    // 校验用户登录信息
    UserModel checkLogin(String telephone,String encPassword) throws Exception;

}
