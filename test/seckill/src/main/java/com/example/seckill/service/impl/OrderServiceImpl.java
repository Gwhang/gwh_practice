package com.example.seckill.service.impl;

import com.example.seckill.dao.OrderInfoDoMapper;
import com.example.seckill.dao.SequenceInfoDoMapper;
import com.example.seckill.dataObject.OrderInfoDo;
import com.example.seckill.dataObject.SequenceInfoDo;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.service.ItemService;
import com.example.seckill.service.OrderService;
import com.example.seckill.service.UserService;
import com.example.seckill.service.model.ItemModel;
import com.example.seckill.service.model.OrderModel;
import com.example.seckill.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sun.awt.EmbeddedFrame;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 订单服务
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderInfoDoMapper orderInfoDoMapper;

    @Autowired
    private SequenceInfoDoMapper sequenceInfoDoMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws Exception{
        // 1.校验下单状态，下单商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel = this.itemService.getItemById(itemId);
        if(StringUtils.isEmpty(itemId)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }
        UserModel userModel=this.userService.getUser(userId);
        if (StringUtils.isEmpty(userModel)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
        }
        if(amount <=0 || amount >99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"数量信息不存在");
        }
        // 2.落单减库存
        boolean flag = this.itemService.decreaseStock(itemId, amount);
        if(!flag){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }
        // 3.订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setOrderPrice(itemModel.getPrice().add(new BigDecimal(amount)));
        //生成交易流水号，订单号
        orderModel.setId(this.generateOrderNo());
        OrderInfoDo orderInfoDo=this.convertFromOrderModel(orderModel);
        this.orderInfoDoMapper.insertSelective(orderInfoDo);
        //加上商品销量
        this.itemService.increaseSales(itemModel.getId(),amount);
        // 4.返回前端
        return orderModel;
    }

    /**
     * 获取订单号
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)//重新开启一个事务
    public String generateOrderNo(){
        StringBuilder stringBuilder=new StringBuilder();
        // 订单16位
        // 前8位为时间信息，年月日
        LocalDateTime now =LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);
        // 中间6位为自增序类
        int sequence = 0;
        //获取序列表信息
        SequenceInfoDo sequenceInfoDo = sequenceInfoDoMapper.getSequenceByName("order_info");
        sequence = sequenceInfoDo.getCurrentValue();
        //设置值
        sequenceInfoDo.setCurrentValue(sequenceInfoDo.getCurrentValue()+sequenceInfoDo.getStep());
        sequenceInfoDoMapper.updateByPrimaryKeySelective(sequenceInfoDo);
        //拼接
        String sequenceStr=String.valueOf(sequence);
        for (int i=0;i<6-sequenceStr.length();i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);
        // 最后2位为分库分表位
       stringBuilder.append("00");
       return stringBuilder.toString();
    }

    private OrderInfoDo convertFromOrderModel(OrderModel orderModel){
        OrderInfoDo orderInfoDo=new OrderInfoDo();
        if(StringUtils.isEmpty(orderModel)){
            return null;
        }
        BeanUtils.copyProperties(orderModel,orderInfoDo);
        return orderInfoDo;
    }

}
