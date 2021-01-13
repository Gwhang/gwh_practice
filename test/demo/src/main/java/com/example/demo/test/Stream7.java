package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 明细根据某个字段去重并分组
 */
public class Stream7 {

    public static void main(String[] args) {

        List<Goods> goodsList=getList();

        Map<String, List<Goods>> map = goodsList.stream().filter(distinctByKey(p -> p.getBatchNo())).collect(Collectors.groupingBy(p -> p.getCategory()));

        System.out.println(JSONObject.toJSONString(map));

    }

    /**
     * 比对属性
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 获取数组
     * @return
     */
    public static List<Goods> getList(){
        List<Goods> list=new ArrayList<>();

        Goods goods1=new Goods("10001","金手镯","18","3000","P0000001");
        Goods goods11=new Goods("10001","金手镯","18","3000","P0000001");
        Goods goods2=new Goods("10002","金戒指","18","1000","P0000002");
        Goods goods22=new Goods("10002","金戒指","18","1000","P0000002");
        Goods goods3=new Goods("10003","金手链","18","900","P0000003");
        Goods goods4=new Goods("10004","K金手镯","19","400","P0000004");
        Goods goods5=new Goods("10005","18K镶钻戒","22","30000","P0000005");
        Goods goods6=new Goods("10006","裸钻","22","18000","P0000006");
        Goods goods7=new Goods("10007","银手镯","16","2000","P0000007");

        list.add(goods1);
        list.add(goods11);
        list.add(goods2);
        list.add(goods22);
        list.add(goods3);
        list.add(goods4);
        list.add(goods5);
        list.add(goods6);
        list.add(goods7);

        return list;
    }

}


/**
 * 商品明细
 */
class Goods{

    private String number;

    private String name;

    private String category;

    private String amount;

    private String batchNo;

    public Goods() {
    }

    public Goods(String number, String name, String category, String amount, String batchNo) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.batchNo = batchNo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}


