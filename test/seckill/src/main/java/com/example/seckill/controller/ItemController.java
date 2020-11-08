package com.example.seckill.controller;

import com.example.seckill.controller.viewObject.ItemVo;
import com.example.seckill.response.CommonReturnType;
import com.example.seckill.service.ItemService;
import com.example.seckill.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品控制器
 */
@Controller
@RequestMapping("/api/item")
@CrossOrigin(allowCredentials = "true",origins = {"*"}) // 跨域接收 解决session中验证码获取为null的问题
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    /**
     * 创建用户
     * @param title
     * @param description
     * @param price
     * @param stock
     * @param imgUrl
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name= "price")BigDecimal price,
                                       @RequestParam(name = "stock")Integer stock,
                                       @RequestParam(name = "imgUrl")String imgUrl)throws Exception{
        //封装service请求用来创建商品
        ItemModel itemModel=new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);
        //调用服务创建商品
        ItemModel item = this.itemService.createItem(itemModel);
        //返回给前台的参数
        ItemVo itemVo = this.convertVoFromModel(itemModel);

        return CommonReturnType.create(itemVo);
    }

    private ItemVo convertVoFromModel(ItemModel itemModel){
        ItemVo itemVo=new ItemVo();
        BeanUtils.copyProperties(itemModel,itemVo);
        if(itemModel.getPromoModel() != null){
            //有正在进行或即将进行的秒杀活动
            itemVo.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVo.setPromoId(itemModel.getPromoModel().getId());
            itemVo.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVo.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else{
            itemVo.setPromoStatus(0);
        }
        return itemVo;
    }

    /**
     * 根据商品ID获取商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id){
        ItemModel itemModel=this.itemService.getItemById(id);
        ItemVo itemVo=this.convertVoFromModel(itemModel);


        return CommonReturnType.create(itemVo);
    }

    /**
     * 获取商品列表
     * @return
     */
    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItemList(){
        List<ItemModel> itemModel=this.itemService.listItem();
        List<ItemVo> itemVoList = itemModel.stream().map(p -> {
            ItemVo itemVo = this.convertVoFromModel(p);
            return itemVo;
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVoList);
    }

}
