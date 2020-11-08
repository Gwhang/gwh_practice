package com.example.seckill.controller;

import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.response.CommonReturnType;
import com.example.seckill.service.OrderService;
import com.example.seckill.service.model.OrderModel;
import com.example.seckill.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单管理
 */
@RequestMapping("/api/order")
@Controller
@CrossOrigin(allowCredentials = "true",origins = {"*"}) // 跨域接收 解决session中验证码获取为null的问题
public class OrderController  extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/createOrder",method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,
                                        @RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name = "promoId",required = false) Integer promoId)throws Exception{
        // 判断用户是否登录 session获取不到 TODO
//        Boolean isLogin= (Boolean)httpServletRequest.getSession().getAttribute("IS_LOGIN");
//        if(isLogin == null || !isLogin.booleanValue()){
//            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
//        }
        // 获取用户登录信息
     //   UserModel userModel = (UserModel)httpServletRequest.getSession().getAttribute("userInfo");
        // 用户信息获取不到固定写 3
        OrderModel orderModel = orderService.createOrder(3, itemId,promoId, amount);
        return CommonReturnType.create(orderModel);
    }

}
