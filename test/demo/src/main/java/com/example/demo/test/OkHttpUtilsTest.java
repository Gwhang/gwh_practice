package com.example.demo.test;

import okhttp3.Call;

public class OkHttpUtilsTest {

    public static void main(String[] args) {

        // get请求，方法顺序按照这种方式，切记选择post/get一定要放在倒数第二，同步或者异步倒数第一，才会正确执行
        String getResult = OkHttpUtils.builder()
                // 填写URL
                .url("http://localhost:8080/httpTest/getTest")
                // 设置参数 如果有多个就设置多个addParam
                .addParam("name", "zhangsan")
                .addParam("age", "24")
                // 也可以添加多个
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .get()
                // 可选择是同步请求还是异步请求
                .sync();
               // .async();
        System.out.println(getResult);


        // post请求，分为两种，一种是普通表单提交，一种是json提交
        String postResult = OkHttpUtils.builder().url("http://localhost:8080/httpTest/postTest")
                // 有参数的话添加参数，可多个
                .addParam("name", "lisi")
                .addParam("age", "123")
                // 也可以添加多个
                .addHeader("Content-Type", "application/json; charset=utf-8")
                // 如果是true的话，会类似于postman中post提交方式的raw，用json的方式提交，不是表单
                // 如果是false的话传统的表单提交
                .post(true)
                .sync();

        System.out.println(postResult);

        // 选择异步有两个方法，一个是带回调接口，一个是直接返回结果
        String postResult2 = OkHttpUtils.builder().url("http://localhost:8080/httpTest/postTest1")
                // 有参数的话添加参数，可多个
                .addParam("name", "wangwu")
                .addParam("age", "67")
                // 如果是false的话传统的表单提交
                .post(false)
                .async();
        System.out.println(postResult2);


        OkHttpUtils.builder().url("http://localhost:8080/httpTest/postTest1")
                // 有参数的话添加参数，可多个
                .addParam("name", "wangwu")
                .addParam("age", "67")
                .post(false)
                .async(new OkHttpUtils.ICallBack() {
            @Override
            public void onSuccessful(Call call, String data) {
                // 请求成功后的处理
                System.out.println("----------------------------");
                System.out.println(data);
            }

            @Override
            public void onFailure(Call call, String errorMsg) {
                // 请求失败后的处理
                System.out.println("----------------------------");
            }
        });



    }



}
