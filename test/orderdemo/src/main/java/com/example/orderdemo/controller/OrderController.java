package com.example.orderdemo.controller;

import com.example.orderdemo.pojo.Logistics;
import com.example.orderdemo.pojo.Order;
import com.example.orderdemo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单控制层
 */
@RestController
public class OrderController {

    Logger logger= LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 添加订单
     * @param order
     * @return
     */
    @PostMapping("addOrder")
    public String addOrder(Order order){
        try {
            order.setStatus("发货中");
            order.setOrderTime(new Date());
            order.setShipTime(new Date());
            orderService.addOrder(order);
        }catch (Exception e){
            return "添加失败";
        }
        return "添加成功";
    }

    /**
     * 更新订单物流信息
     * @param logistics
     * @return
     */
    @PostMapping("/updateOrder")
    public String updateOrder(Logistics logistics){
        try {
        logistics.setOperationTime(new Date());
        orderService.addLogisticsAndUpdateStatus(logistics);
        }catch (Exception e){
            e.printStackTrace();
            return "更新物流信息失败";

        }
        return "更新物流信息成功";
    }

    /**
     * 查询订单信息
     * @param id
     * @return
     */
    @GetMapping("getOrderById")
    public Order getOrderById(@RequestParam("id") int id){
        Order order = orderService.getOrderById(id);
        return order;
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public String deleteById(int id){
        boolean flag=orderService.deleteOrderById(id);
        if(flag){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 分页查询数据
     * @return
     */
    @GetMapping("getAllOrder")
    public Map<String,Object> getAllOrder(){
        Map<String,Object>map=new HashMap<>();
        List<Order> list=orderService.getAllorder();
        map.put("code","0");
        map.put("count",list.size());
        map.put("data",list);
        return  map;
    }
}
