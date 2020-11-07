package com.example.seckill.service.impl;

import com.example.seckill.controller.viewObject.UserVo;
import com.example.seckill.dao.UserDaoMapper;
import com.example.seckill.dao.UserPassWordMapper;
import com.example.seckill.dataObject.UserDao;
import com.example.seckill.dataObject.UserPassWord;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.service.UserService;

import com.example.seckill.service.model.UserModel;
import com.example.seckill.validator.ValidationResult;
import com.example.seckill.validator.ValidatorImpl;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.beans.Transient;

/**
 * 用户接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    public UserDaoMapper userDaoMapper;
    @Autowired
    public UserPassWordMapper userPassWordMapper;
    @Autowired
    public ValidatorImpl validator;
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

    /**
     * 用户注册
     * @param userModel
     */
    @Override
    @Transient
    public void register(UserModel userModel) throws Exception {
        if(StringUtils.isEmpty(userModel)){
            logger.info("用户注册 对象为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
//        if(StringUtils.isEmpty(userModel.getName())||StringUtils.isEmpty(userModel.getGender())
//            ||StringUtils.isEmpty(userModel.getTelphone())){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
        //校验优化
        ValidationResult validate = validator.validate(userModel);
        if (validate.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validate.getErrMsg());
        }
        //校验手机号是否存在
        int count = userDaoMapper.selectByTel(userModel.getTelphone());
        if(count>0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户手机号重复");
        }
        // 实现 model ->DO 的实现
        UserDao userDao=converFromModel(userModel);
        userDaoMapper.insertSelective(userDao);
        userModel.setId(userDao.getId());
        UserPassWord userPassWord=converFromPassword(userModel);
        userPassWordMapper.insertSelective(userPassWord);
        return;
    }

    private UserPassWord converFromPassword(UserModel userModel){
        if(StringUtils.isEmpty(userModel)){
            return null;
        }
        UserPassWord userPassWord = new UserPassWord();
        userPassWord.setEncrptPassword(userModel.getEncrptPassword());
        userPassWord.setUserId(userModel.getId());
        return userPassWord;
    }

    /**
     * 返回do对象
     * @param userModel
     * @return
     */
    private UserDao converFromModel(UserModel userModel){
        if(StringUtils.isEmpty(userModel)){
            return null;
        }
        UserDao userDao = new UserDao();
        BeanUtils.copyProperties(userModel,userDao);
        return userDao;
    }

    /**
     * 返回model对象
     * @param userDao
     * @param userPassWord
     * @return
     */
    private UserModel converFromDateObject(UserDao userDao,UserPassWord userPassWord){
        if(StringUtils.isEmpty(userDao)){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel,userDao);
        BeanUtils.copyProperties(userModel,userPassWord);
        return  userModel;
    }

    /**
     * 校验用户信息
     * @param telephone 手机号
     * @param encPassword 加密后的密码
     * @return
     */
    @Override
    public UserModel checkLogin(String telephone, String encPassword) throws Exception{

        // 获取用户信息 根据手机号
        UserDao userDo=this.userDaoMapper.selectByTelephone(telephone);
        if (StringUtils.isEmpty(userDo)){
            throw new BusinessException(EmBusinessError.USER_LOGIN_EXIST);
        }
        //获取密码信息
        UserPassWord userPassWord=this.userPassWordMapper.selectUserById(userDo.getId());
        //设置用户信息
        UserModel userModel=this.converFromDateObject(userDo,userPassWord);
        if (com.alibaba.druid.util.StringUtils.equals(userModel.getEncrptPassword(),encPassword)){
            throw  new BusinessException(EmBusinessError.USER_LOGIN_EXIST);
        }
        return userModel;
    }
}
