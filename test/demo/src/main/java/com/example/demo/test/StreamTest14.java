package com.example.demo.test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 使用stream将字符串分割组装成list
 */
public class StreamTest14 {

    public static void main(String[] args) {
        String url = "你好,太棒了,加油";

        List<String> list = Stream.of(url.split(",")).collect(Collectors.toList());

        System.out.println(list);

    }

}
