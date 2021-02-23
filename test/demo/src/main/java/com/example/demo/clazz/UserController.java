package com.example.demo.clazz;

public class UserController {

    private User[] user={
            new User("张三"),
            new User("李四"),
            new User("王五")
    };


    @Cache(key = "user")
    public User getUserById(Integer id){
        System.out.println("根据缓存查询用户数据");
        return user[id];
    }

}
