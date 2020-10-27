package com.example.seckill.service.impl;

import com.example.seckill.dao.UserDaoMapper;
import com.example.seckill.dao.UserPassWordMapper;
import com.example.seckill.dataObject.UserDao;
import com.example.seckill.dataObject.UserPassWord;
import com.example.seckill.service.UserService;

import com.example.seckill.service.model.UserModel;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 用户接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    public UserDaoMapper userDaoMapper;
    @Resource
    public UserPassWordMapper userPassWordMapper;

    /**
     * 获取用户对象信息
     * @param id
     */
    @Override
    public UserModel getUser(Integer id) {
        //根据ID获取用户对象信息
        UserDao user=userDaoMapper.selectByPrimaryKey(id);
        if(StringUtils.isEmpty(user)){
            logger.info("根据ID查询用户信息为空,{}",id);
            return null;
        }
        //通过用户ID查询密码信息
        UserPassWord userPassWord=userPassWordMapper.selectUserById(id);
        return convertFromDataObject(user,userPassWord);
    }

    /**
     * 设置返回对象
     * @param userDao
     * @param userPassWord
     * @return
     */
    public UserModel convertFromDataObject(UserDao userDao,UserPassWord userPassWord){
       UserModel userModel=new UserModel();
       if(StringUtils.isEmpty(userDao)){
           logger.info("拼接用户返回对象，用户信息为空");
           return null;
       }
       //copy用户信息
        BeanUtils.copyProperties(userDao,userModel);
       if(!StringUtils.isEmpty(userPassWord)){
           userModel.setEncrptPassword(userPassWord.getEncrptPassword());
       }

       return userModel;
    }


}
