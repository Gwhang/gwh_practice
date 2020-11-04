package com.example.demo.test;

import org.apache.commons.compress.utils.Lists;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 比较两个集合设置集合对象中的属性
 */
public class streamTest4 {

    public static void main(String[] args) {
        // 获取商品对象
        List<GoodInfo> goodInfos=getGoodModelList();
        List<ImgInfo> imgInfo=getImgInfo();
        System.out.println("===== 商品属性集合 =====");
        System.out.println(goodInfos);
        // 设置另外一个集合的对象属性
        for (ImgInfo img:imgInfo){
            goodInfos.stream().filter(p -> img.getGoodNumber().equals(p.getNumber())).forEach(
                    p -> {p.setImg(img.getImg());p.setImgDescribe(img.getImgDescribe());}
            );
        }
        System.out.println("===== 设置后的属性集合 =====");
        System.out.println(goodInfos);

        // 获取所有商品的总金额 BigDecimal 求和
        System.out.println("===== 求和 =====");
        BigDecimal price=goodInfos.stream().map(GoodInfo::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        // 基本类型求和
        int count=goodInfos.stream().mapToInt(GoodInfo::getCount).sum();
        System.out.println("商品总金额为:"+price);
        System.out.println("商品总件数:"+count);
        // 最贵的商品 求金额最大值  如果比较件数，后面的使用Integer
        System.out.println("===== 最大值 =====");
        BigDecimal maxPrice=goodInfos.stream().map(GoodInfo::getPrice).max(BigDecimal::compareTo).get();
        Integer maxCount=goodInfos.stream().map(GoodInfo::getCount).max(Integer::compareTo).get();
        System.out.println("最贵的商品价格为："+maxPrice);
        // 求金额最小值
        System.out.println("===== 最小值 =====");
        BigDecimal minPrice=goodInfos.stream().map(GoodInfo::getPrice).min(BigDecimal::compareTo).get();
        System.out.println("最便宜的商品价格为："+minPrice);
        // list转map
        System.out.println("===== 转map =====");
        Map<String,GoodInfo> mapGoodInfo=goodInfos.stream().collect(Collectors.toMap(GoodInfo::getNumber,p -> p,(k1,k2)->k1));
        System.out.println(mapGoodInfo);
        // 排序 根据商品升序
        System.out.println("===== 排序 =====");
        goodInfos.stream().sorted(Comparator.comparing(GoodInfo::getPrice));
        System.out.println(goodInfos);
        // 分组
        System.out.println("===== 分组 =====");
        Map<String,List<GoodInfo>> groupMap=goodInfos.stream().collect(Collectors.groupingBy(GoodInfo::getSerialNumber));
        // 遍历分组
        List<GoodInfo> entryUserList=Lists.newArrayList();
        for (Map.Entry<String, List<GoodInfo>> entryUser : groupMap.entrySet()) {
            String key = entryUser.getKey();
            entryUserList = entryUser.getValue();
            System.out.println("key:"+key);
            System.out.println("value:"+entryUserList);
        }



    }

    public static List<ImgInfo> getImgInfo(){
        List<ImgInfo> imgInfos=Lists.newArrayList();

        ImgInfo imgInfo=new ImgInfo();
        imgInfo.setGoodNumber("10001");
        imgInfo.setImg("这是一张小米8SE的图片.jpg");
        imgInfo.setImgDescribe("小米8SE");
        ImgInfo imgInfo1=new ImgInfo();
        imgInfo1.setGoodNumber("10003");
        imgInfo1.setImg("这是一张小米的图片.jpg");
        imgInfo1.setImgDescribe("小米8");
        imgInfos.add(imgInfo);
        imgInfos.add(imgInfo1);
        return imgInfos;
    }


    public static List<GoodInfo> getGoodModelList(){
        List<GoodInfo> goodModelList= Lists.newArrayList();
        GoodInfo goodModel=new GoodInfo();
        goodModel.setNumber("10001");
        goodModel.setSerialNumber("X243");
        goodModel.setName("小米8SE");
        goodModel.setDescribe("8G运行内存 128G机身内存 不支持NFC");
        goodModel.setCount(20);
        goodModel.setPrice(new BigDecimal("1899"));
        GoodInfo goodModel1=new GoodInfo();
        goodModel1.setNumber("10002");
        goodModel1.setSerialNumber("X243");
        goodModel1.setName("小米8青春版");
        goodModel1.setDescribe("8G运行内存 128G机身内存 外形美观");
        goodModel1.setCount(20);
        goodModel1.setPrice(new BigDecimal("2999"));
        GoodInfo goodModel2=new GoodInfo();
        goodModel2.setNumber("10003");
        goodModel2.setSerialNumber("X477");
        goodModel2.setName("小米8");
        goodModel2.setDescribe("8G运行内存 128G机身内存 外形美观 支持NFC");
        goodModel2.setCount(20);
        goodModel2.setPrice(new BigDecimal("1999"));
        GoodInfo goodModel3=new GoodInfo();
        goodModel3.setNumber("10004");
        goodModel3.setSerialNumber("X499");
        goodModel3.setName("小米10");
        goodModel3.setDescribe("16G运行内存 256G机身内存 外形美观 支持NFC");
        goodModel3.setCount(20);
        goodModel3.setPrice(new BigDecimal("9999"));
        goodModelList.add(goodModel);
        goodModelList.add(goodModel1);
        goodModelList.add(goodModel2);
        goodModelList.add(goodModel3);
        return goodModelList;
    }

}

class GoodInfo{

    private String number;

    private String name;

    private String SerialNumber;

    private String describe;

    private BigDecimal price;

    private Integer count;

    private String img;

    private String imgDescribe;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgDescribe() {
        return imgDescribe;
    }

    public void setImgDescribe(String imgDescribe) {
        this.imgDescribe = imgDescribe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "GoodInfo{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", describe='" + describe + '\'' +
                ", price='" + price + '\'' +
                ", img='" + img + '\'' +
                ", imgDescribe='" + imgDescribe + '\'' +
                '}';
    }
}

class ImgInfo{

    private String goodNumber;
    private String img;
    private String imgDescribe;

    public String getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(String goodNumber) {
        this.goodNumber = goodNumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgDescribe() {
        return imgDescribe;
    }

    public void setImgDescribe(String imgDescribe) {
        this.imgDescribe = imgDescribe;
    }
}
