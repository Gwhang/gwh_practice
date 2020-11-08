package com.example.seckill.service.impl;

import com.example.seckill.dao.ItemDoMapper;
import com.example.seckill.dao.ItemStockDoMapper;
import com.example.seckill.dataObject.ItemDo;
import com.example.seckill.dataObject.ItemStockDo;
import com.example.seckill.error.BusinessException;
import com.example.seckill.error.EmBusinessError;
import com.example.seckill.service.ItemService;
import com.example.seckill.service.PromoService;
import com.example.seckill.service.model.ItemModel;
import com.example.seckill.service.model.PromoModel;
import com.example.seckill.validator.ValidationResult;
import com.example.seckill.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品信息服务
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDoMapper itemDoMapper;

    @Autowired
    private ItemStockDoMapper itemStockDoMapper;

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private PromoService promoService;

    /**
     * 创建商品
     * @param itemModel
     * @return
     */
    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws Exception{
        // 校验入参
        ValidationResult validate = this.validator.validate(itemModel);
        if(validate.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validate.getErrMsg());
        }
        // 转换itemmodel->dataobject
        ItemDo itemDo = this.convertItemDoFromItemModel(itemModel);
        // 写入数据库
        itemDoMapper.insertSelective(itemDo);
        //获取商品ID
        itemModel.setId(itemDo.getId());
        ItemStockDo itemStockDo = this.convertItemStockDoFormItemModel(itemModel);
        itemStockDoMapper.insertSelective(itemStockDo);
        //返回创建完成的对象
        return this.getItemById(itemDo.getId());
    }
    //获取商品信息
    private ItemDo convertItemDoFromItemModel(ItemModel itemModel){
        if(StringUtils.isEmpty(itemModel)){
            return null;
        }
        ItemDo itemDo=new ItemDo();
        BeanUtils.copyProperties(itemModel,itemDo);
        return itemDo;
    }
    //获取库存信息
    private ItemStockDo convertItemStockDoFormItemModel(ItemModel itemModel){
        if (StringUtils.isEmpty(itemModel)){
            return null;
        }
        ItemStockDo itemStockDo=new ItemStockDo();
        itemStockDo.setItemId(itemModel.getId());
        itemStockDo.setStock(itemModel.getStock());
        return itemStockDo;
    }

    /**
     * 查询商品列表
     * @return
     */
    @Override
    public List<ItemModel> listItem() {
        List<ItemDo> itemDoList=this.itemDoMapper.selectItemList();
        if (StringUtils.isEmpty(itemDoList)){
            return null;
        }
        //设置model对象
        List<ItemModel> itemModelList = itemDoList.stream().map(p -> {
            ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(p.getId());
            ItemModel itemModel = this.convertModelFormDataObject(p, itemStockDo);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    /**
     * 获取商品信息
     * @param id
     * @return
     */
    @Override
    public ItemModel getItemById(Integer id) {
        ItemDo itemDo=this.itemDoMapper.selectByPrimaryKey(id);
        if (StringUtils.isEmpty(itemDo)){
            return null;
        }
        // 操作获得库存数量
        ItemStockDo itemStockDo=this.itemStockDoMapper.selectByItemId(id);
        // 将dataobject ->model
        ItemModel itemModel = this.convertModelFormDataObject(itemDo, itemStockDo);
        //获取秒杀活动商品信息
        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if(promoModel != null && promoModel.getStatus().intValue() != 3){
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }
    private ItemModel convertModelFormDataObject(ItemDo itemDo,ItemStockDo itemStockDo){
        ItemModel itemModel=new ItemModel();
        BeanUtils.copyProperties(itemDo,itemModel);
        itemModel.setStock(itemStockDo.getStock());
        return itemModel;
    }

    /**
     * 库存扣减
     * @param itemId
     * @param amount
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws Exception {
      int affectedRow = this.itemStockDoMapper.decreaseStock(itemId,amount);
      if(affectedRow > 0){
          //更新库存成功
          return true;
      }
        return false;
    }

    /**
     * 销量增加
     * @param itemId
     * @param amount
     * @throws Exception
     */
    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws Exception {
        this.itemDoMapper.increaseSales(itemId,amount);

    }
}
