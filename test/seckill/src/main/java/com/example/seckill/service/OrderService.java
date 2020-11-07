package com.example.seckill.service;

import com.example.seckill.service.model.OrderModel;

/**
 * 订单接口
 */
public interface OrderService {

    // 创建订单
    OrderModel createOrder(Integer userId,Integer itemId,Integer amount) throws Exception;

}
