package com.example.seckill.service;

import com.example.seckill.service.model.OrderModel;

/**
 * 订单接口
 */
public interface OrderService {

    // 创建订单
    // 使用 1.通过前端url上传过了秒杀活动的id,然后下单接口内校验对应id是否属于对应商品且活动已开始
    //2.直接在下单接口内判断对应的商品是否存在秒杀活动，若存在进行中的则以秒杀价格下单
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId,Integer amount) throws Exception;

}
