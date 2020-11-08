package com.example.seckill.service.impl;

import com.example.seckill.dao.PromoDoMapper;
import com.example.seckill.dataObject.PromoDo;
import com.example.seckill.service.PromoService;
import com.example.seckill.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 秒杀服务
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDoMapper promoDoMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        //获取对应商品的秒杀活动ID
        PromoDo promoDo = this.promoDoMapper.selectByItemId(itemId);
        if (StringUtils.isEmpty(promoDo)){
            return null;
        }
        PromoModel promoModel = this.convertFromDataObject(promoDo);
        //判断当前时间是否秒杀活动即将开始或正在进行
        DateTime now =new DateTime();
        if(promoModel.getStartDate().isAfterNow()){ //如果开始时间比现在时间还要晚，那么秒杀还未开始
            promoModel.setStatus(1);//还未开始
        }else if(promoModel.getEndDate().isBeforeNow()){//如果结束时间比现在时间还要晚，那么秒杀结束
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2); //正在进行中
        }

        return promoModel;
    }

    private  PromoModel convertFromDataObject(PromoDo promoDo){
        if(StringUtils.isEmpty(promoDo)){
            return null;
        }
        PromoModel promoModel=new PromoModel();
        BeanUtils.copyProperties(promoDo,promoModel);
        promoModel.setStartDate(new DateTime(promoDo.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDo.getEndDate()));
        return promoModel;
    }

}
