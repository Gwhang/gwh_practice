package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.compress.utils.Lists;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 读取本地json文件并查出数据问题
 */
public class ReadTxtFormJson {

    /**
     * 读取本地json数据
     * @param filePath
     * @return
     */
    public static String readJsonFile(String filePath) {
        String jsonStr = "";
        try {
            //读取json文件内容
            File jsonFile = new File(filePath);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        String path = ReadTxtFormJson.class.getClassLoader().getResource("pawn.json").getPath();
        //读取json文件内容
        String s = readJsonFile(path);
        //将字符串转换为数组
        List<Map<String,String>> json = JSONArray.parseObject(s,List.class);
        //遍历数组
        json.stream().forEach(p -> {
            List<String> list= Lists.newArrayList();
            //获取单个对象中的数组 将其转换为List集合
            List<PawnItems> items = JSONArray.parseArray(JSON.toJSONString(p.get("ITEMS")),PawnItems.class);
            //获取金额为空的数据
            list=items.stream().filter(i -> i.getPWN_AMOUNT()==null||i.getPWN_AMOUNT()=="").map(PawnItems::getBATCH_NO).collect(Collectors.toList());
            //遍历满足条件的数据
            list.stream().forEach(System.out::println);
        });

    }


}
class PawnItems{

    private String BATCH_NO;

    private String CATEGORY;

    private String CATEGORY_NAME;

    private String LARGE_CLAZZ;

    private String LITTLE_CLAZZ;

    private String MATERIAL_CODE;

    private String PAWN_NAME;
    private String PAWN_RECEIVE_DATE;

    private String PWN_AMOUNT;
    private String QTY;
    private String SPEC_AND_STATUS;

    public String getBATCH_NO() {
        return BATCH_NO;
    }

    public void setBATCH_NO(String BATCH_NO) {
        this.BATCH_NO = BATCH_NO;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getCATEGORY_NAME() {
        return CATEGORY_NAME;
    }

    public void setCATEGORY_NAME(String CATEGORY_NAME) {
        this.CATEGORY_NAME = CATEGORY_NAME;
    }

    public String getLARGE_CLAZZ() {
        return LARGE_CLAZZ;
    }

    public void setLARGE_CLAZZ(String LARGE_CLAZZ) {
        this.LARGE_CLAZZ = LARGE_CLAZZ;
    }

    public String getLITTLE_CLAZZ() {
        return LITTLE_CLAZZ;
    }

    public void setLITTLE_CLAZZ(String LITTLE_CLAZZ) {
        this.LITTLE_CLAZZ = LITTLE_CLAZZ;
    }

    public String getMATERIAL_CODE() {
        return MATERIAL_CODE;
    }

    public void setMATERIAL_CODE(String MATERIAL_CODE) {
        this.MATERIAL_CODE = MATERIAL_CODE;
    }

    public String getPAWN_NAME() {
        return PAWN_NAME;
    }

    public void setPAWN_NAME(String PAWN_NAME) {
        this.PAWN_NAME = PAWN_NAME;
    }

    public String getPAWN_RECEIVE_DATE() {
        return PAWN_RECEIVE_DATE;
    }

    public void setPAWN_RECEIVE_DATE(String PAWN_RECEIVE_DATE) {
        this.PAWN_RECEIVE_DATE = PAWN_RECEIVE_DATE;
    }

    public String getPWN_AMOUNT() {
        return PWN_AMOUNT;
    }

    public void setPWN_AMOUNT(String PWN_AMOUNT) {
        this.PWN_AMOUNT = PWN_AMOUNT;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getSPEC_AND_STATUS() {
        return SPEC_AND_STATUS;
    }

    public void setSPEC_AND_STATUS(String SPEC_AND_STATUS) {
        this.SPEC_AND_STATUS = SPEC_AND_STATUS;
    }
}