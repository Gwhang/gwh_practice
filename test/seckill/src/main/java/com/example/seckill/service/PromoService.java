package com.example.seckill.service;

import com.example.seckill.service.model.PromoModel;

/**
 * 秒杀接口
 */
public interface PromoService {

    //根据商品id获取正在进行或者将要进行的描述商品信息
    PromoModel getPromoByItemId(Integer itemId);

}
