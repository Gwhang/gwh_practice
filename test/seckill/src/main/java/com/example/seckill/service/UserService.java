package com.example.seckill.service;

import com.example.seckill.service.model.UserModel;

/**
 * 用户实现类接口
 */
public interface UserService {

    // 获取用户信息
    UserModel getUser(Integer id);
    // 创建用户
    void register(UserModel userModel) throws Exception;

}
