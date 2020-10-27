package com.example.seckill.controller;

import com.example.seckill.controller.viewObject.UserVo;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import com.example.seckill.service.UserService;
import com.example.seckill.service.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 */
@RequestMapping("/user")
public class UserController extends BaseController{

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam("id") Integer id) throws Exception{
        //调用service服务获取对应id的用户信息返回给前端
        UserModel userModel = userService.getUser(id);
        //若获取对应用户信息不存在
        if(userModel == null){
            throw  new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域模型用户对象转换为可供UI使用的viewObject
        UserVo userVo = convertFromObject(userModel);
        //返回通用对象
        return CommonReturnType.create(userVo);
    }

    /**
     * 转换对象，设置为前台所需属性
     * @param userModel
     * @return
     */
    public UserVo convertFromObject(UserModel userModel){
        UserVo userVo=new UserVo();
        if(StringUtils.isEmpty(userVo)){
            logger.info("查询用户对象信息为空");
            return null;
        }
        BeanUtils.copyProperties(userModel,userVo);
        return userVo;
    }




}
