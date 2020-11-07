package com.example.seckill.service;

import com.example.seckill.service.model.ItemModel;

import java.util.List;

/**
 * 商品服务
 */
public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel);
    //商品列表浏览
    List<ItemModel> listItem();
    //商品详情浏览
    ItemModel getItemById(Integer id);

}
