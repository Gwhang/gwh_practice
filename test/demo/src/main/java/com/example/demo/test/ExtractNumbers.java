package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  java提取出一个字符串里面的Double类型数字
 */
public class ExtractNumbers {

    public static void main(String[] args) {
        String str="金饰品86.3333克";
        String regex="\\d+(?:\\.\\d+)?";
        Matcher m= Pattern.compile(regex, Pattern.MULTILINE).matcher(str);
        List<String> result=new ArrayList<String>();
//        while(m.find()){
//            result.add(m.group());
//        }
        if(m.find()){
            System.out.println(m.group());
        }

    }

}
