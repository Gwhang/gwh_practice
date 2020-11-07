package com.example.seckill.controller;

import com.example.seckill.controller.viewObject.UserVo;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import com.example.seckill.service.UserService;
import com.example.seckill.service.model.UserModel;
import com.example.seckill.validator.ValidatorImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用户管理
 */
@RequestMapping("/api/user")
@Controller
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*") // 跨域接收 解决session中验证码获取为null的问题
public class UserController extends BaseController{

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService userService;

    @Autowired
    public HttpServletRequest httpServletRequest;


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

    /**
     * 获取用户验证码并发送
     * @param telephone
     * @return
     */
    @RequestMapping("/getotp")
    @ResponseBody
    public CommonReturnType getOpt(@RequestParam("telephone")String telephone){
        //需要按照规则生成OPT验证码
        Random random=new Random();
        int randomInt = random.nextInt(999999);
        randomInt += 100000;
        String optCode=String.valueOf(randomInt);
        // 将OPT验证码同对应用户手机号关联，使用httpsession的方式绑定OPTcode和用户的手机号
        httpServletRequest.getSession().setAttribute(telephone,optCode);
        // 设置session有效时长
        httpServletRequest.getSession().setMaxInactiveInterval(30*60);
        //  String sessionOptCode=(String)this.httpServletRequest.getSession().getAttribute(telephone);
        // 将OPT验证码通过短信通道发送给用户
        logger.info("telephone={}&optCode={}",telephone,optCode);

        return CommonReturnType.create(null);
    }

    /**
     * 用户注册
     * @param telephone
     * @param otpCode
     * @param name
     * @param gender
     * @param age
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public CommonReturnType registe(String telephone,String otpCode,String name,
                                    Integer gender,Integer age,
                                    String password) throws Exception{
        // TODO 验证手机号和对应的otpcode相符合 遗留问题，session获取不到 后期使用redis改进
    //    String sessionOptCode=(String)this.httpServletRequest.getSession().getAttribute(telephone);
        // StringUtils.equals 底层有判空的操作
  //      if(!com.alibaba.druid.util.StringUtils.equals(sessionOptCode,otpCode)){
    //        throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码与手机不符合");
  //      }
        //用户注册流程
        UserModel userModel=new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelphone(telephone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(DigestUtils.md5Hex(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="telephone") String telephone,
                                  @RequestParam(name="password") String password) throws Exception{
        //入参校验
        if(com.alibaba.druid.util.StringUtils.isEmpty(telephone) ||
                com.alibaba.druid.util.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 用户登录服务，用来校验用户登录是否合法
        UserModel userModel = userService.checkLogin(telephone,DigestUtils.md5Hex(password));
        // 将登录凭证加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("userInfo",userModel);
        return CommonReturnType.create(null);
    }

}
