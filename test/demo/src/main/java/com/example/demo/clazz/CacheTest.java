package com.example.demo.clazz;



public class CacheTest {

    public static void main(String[] args) throws Exception {

        UserController userController= new UserController();

        Object user1 = MethodUtil.CacheMethod(userController, "getUserById", 1);
        Object user2 = MethodUtil.CacheMethod(userController, "getUserById", 1);

    }

}
