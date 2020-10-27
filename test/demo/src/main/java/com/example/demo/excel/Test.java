package com.example.demo.excel;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws Exception{
        ImportExcelUtil importExcelUtil=new ImportExcelUtil();

        List<Map<Integer, Object>> maps = importExcelUtil.readExcelContent("C:\\Users\\admin\\Desktop\\大兴王府井票号.xlsx", null, null);
        System.out.println(JSONArray.toJSONString(maps));

    }

}
