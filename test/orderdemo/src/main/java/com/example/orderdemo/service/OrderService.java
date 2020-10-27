package com.example.orderdemo.service;

import com.example.orderdemo.pojo.Logistics;
import com.example.orderdemo.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单业务层
 */
@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 添加订单
     * 插入的语句为 mongoTemplate.insert(order,"order")，第一个参数为插入的文档记录，
     * 第二个参数"order"为连接的MongoDB对应数据库下的集合(Collections)
     * @param order
     */
    public void addOrder(Order order){
        mongoTemplate.insert(order,"order");
    }

    /**
     * 更新订单物流信息
     * Query对象来辅助我们根据条件查询待更新数据，这里的意思就是查询数据的条件为：MongoDB中_id字段为具体物流对应的订单id。
     * Update对象用来设置更新的字段和数据，其set()方法用来直接更新对应字段的值，而push()方法用来向对应数组中追加数值。因为订单状态需要直接更新使用set()方法，而物流信息是由多个物流数据组成，所以需要使用push()追加到记录的后面。
     * mongoTemplate.upsert(query, update, order.class)用来实现更新操作的提交，如果MongoDB中不存在该数据那么就插入到MongoDB中。
     * @param logistics
     */
    public void addLogisticsAndUpdateStatus(Logistics logistics){
        String status=logistics.getOperation();
        //根据订单编号查询订单信息，并更新状态新增一条数据保存
        Query query=new Query(Criteria.where("_id").is(logistics.getOrderId()));
        Update update=new Update();
        update.set("status",status);
        update.push("logistics",logistics);
        mongoTemplate.upsert(query,update,Order.class);
    }

    /**
     * 查询订单信息
     * Query对象来辅助我们实现条件查询待更新数据，这里的意思就是查询条件同样为：MongoDB中_id字段为传进来id的该条记录。
     * 查询一条记录语句为：mongoTemplate.findOne(query, order.class)，第一个参数为查询的条件，第二个参数为查询结果转成Java对象的类型，它帮你自动处理。
     * 如果查询多条记录即可用findAll()方法，返回的类型为List的集合类型。
     * @param id
     * @return
     */
    public Order getOrderById(int id){
        Query query = new Query(Criteria.where("_id").is(Integer.valueOf(id)));
        Order order=mongoTemplate.findOne(query, Order.class);
        return order;
    }

    /**
     * 删除订单
     * 删除的语句为 mongoTemplate.remove(query,order.class,“order”);
     * 第一个参数为待删除记录的定位条件，第二个参数为待删除数据的Java类型，第三个参数为待删除数据所在集合名称。
     * @param id
     * @return
     */
    public boolean deleteOrderById(int id){
        Query query = new Query(Criteria.where("_id").is(id));
        try {
            mongoTemplate.remove(query,Order.class);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 查询所有数据
     * 查询所有记录语句为：mongoTemplate.findAll(order.class,“order”);
     * 第一个参数为查询结果转成Java对象的类型，它帮你自动处理。第二个参数为待查询的集合
     * @return
     */
    public List<Order> getAllorder(){
        List<Order> orderList=mongoTemplate.findAll(Order.class,"order");
        return orderList;
    }


}
