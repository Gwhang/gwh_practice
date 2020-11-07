package com.example.seckill.service;

import com.example.seckill.service.model.ItemModel;

import java.util.List;

/**
 * 商品服务
 */
public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws Exception;
    //商品列表浏览
    List<ItemModel> listItem();
    //商品详情浏览
    ItemModel getItemById(Integer id);

    //库存扣减
    boolean decreaseStock(Integer itemId,Integer amount) throws Exception;

    //商品销量增加
    void increaseSales(Integer itemId,Integer amount) throws Exception;

}
