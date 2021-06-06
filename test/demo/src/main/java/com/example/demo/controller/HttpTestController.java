package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * okHttp验证
 */
@RestController
@RequestMapping("/httpTest")
public class HttpTestController {


    @GetMapping("/getTest")
    public Map<String,String> getTest(String name,String age){

        Map<String,String> map = new HashMap<String,String>();

        map.put("name",name);
        map.put("age",age);

        return map;
    }

    @PostMapping("/postTest")
    public Map<String,String> postTest(@RequestBody User user){

        Map<String,String> map = new HashMap<String,String>();

        map.put("name",user.getName());
        map.put("age",user.getAge());

        return map;
    }

    @PostMapping("/postTest1")
    public Map<String,String> postTest1(String name,String age){

        Map<String,String> map = new HashMap<String,String>();

        map.put("name",name);
        map.put("age",age);

        return map;
    }


}
