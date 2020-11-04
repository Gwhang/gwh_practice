package com.example.demo.test;


import org.apache.catalina.LifecycleState;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 两个集合比较 取出差集
 */
public class streamTest2 {

    public static void main(String[] args) {
        List<GoodModel> goodModelList=getGoodModelList();
        List<GoodVo> goodVoList=getGoodVoList();
        //比较两个集合，去除goodVoList中包含goodModelList的商品
        List<GoodVo> goodVos=goodVoList.stream().filter(p ->
                !goodModelList.stream()
                        .map(b ->b.getNumber()).collect(Collectors.toList())
                        .contains(p.getNumber())).collect(Collectors.toList());
        System.out.println("===== 过滤商品列表中已经销售的商品 =====");
        goodVos.stream().forEach(System.out::println);

    }

    public static List<GoodModel> getGoodModelList(){
        List<GoodModel> goodModelList= Lists.newArrayList();
        GoodModel goodModel=new GoodModel();
        goodModel.setNumber("10001");
        goodModel.setSerialNumber("X243");
        goodModel.setName("小米8SE");
        goodModel.setDescribe("8G运行内存 128G机身内存 不支持NFC");
        goodModel.setPrice("1899");
        GoodModel goodModel1=new GoodModel();
        goodModel1.setNumber("10002");
        goodModel1.setSerialNumber("X266");
        goodModel1.setName("小米8青春版");
        goodModel1.setDescribe("8G运行内存 128G机身内存 外形美观");
        goodModel1.setPrice("1999");
        GoodModel goodModel2=new GoodModel();
        goodModel2.setNumber("10003");
        goodModel2.setSerialNumber("X477");
        goodModel2.setName("小米8");
        goodModel2.setDescribe("8G运行内存 128G机身内存 外形美观 支持NFC");
        goodModel2.setPrice("1999");
        GoodModel goodModel3=new GoodModel();
        goodModel3.setNumber("10004");
        goodModel3.setSerialNumber("X499");
        goodModel3.setName("小米10");
        goodModel3.setDescribe("16G运行内存 256G机身内存 外形美观 支持NFC");
        goodModel3.setPrice("9999");
        goodModelList.add(goodModel);
        goodModelList.add(goodModel1);
        goodModelList.add(goodModel2);
        goodModelList.add(goodModel3);
        return goodModelList;
    }
    public static List<GoodVo> getGoodVoList(){
        List<GoodVo> goodVoList= Lists.newArrayList();
        GoodVo goodVo=new GoodVo();
        goodVo.setNumber("10001");
        goodVo.setSerialNumber("X243");
        goodVo.setName("小米8SE");
        goodVo.setDescribe("8G运行内存 128G机身内存 不支持NFC");
        goodVo.setPrice("1899");
        GoodVo goodVo1=new GoodVo();
        goodVo1.setNumber("10002");
        goodVo1.setSerialNumber("X266");
        goodVo1.setName("小米8青春版");
        goodVo1.setDescribe("8G运行内存 128G机身内存 外形美观");
        goodVo1.setPrice("1999");
        GoodVo goodVo2=new GoodVo();
        goodVo2.setNumber("10003");
        goodVo2.setSerialNumber("X477");
        goodVo2.setName("小米8");
        goodVo2.setDescribe("8G运行内存 128G机身内存 外形美观 支持NFC");
        goodVo2.setPrice("1999");
        GoodVo goodVo3=new GoodVo();
        goodVo3.setNumber("10006");
        goodVo3.setSerialNumber("XF77");
        goodVo3.setName("华为 not 8");
        goodVo3.setDescribe("8G运行内存 128G机身内存 外形美观 支持NFC");
        goodVo3.setPrice("2999");
        goodVoList.add(goodVo);
        goodVoList.add(goodVo1);
        goodVoList.add(goodVo2);
        goodVoList.add(goodVo3);
        return goodVoList;
    }

}

class GoodModel{

    private String number;

    private String name;

    private String SerialNumber;

    private String describe;

    private String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
}
class GoodVo{

    private String number;

    private String name;

    private String SerialNumber;

    private String describe;

    private String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GoodVo{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", describe='" + describe + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}






