package com.example.seckill.service;

import com.example.seckill.service.model.UserModel;

/**
 * 用户实现类接口
 */
public interface UserService {

    //获取用户信息
    public UserModel getUser(Integer id);

}
