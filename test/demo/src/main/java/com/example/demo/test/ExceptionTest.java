package com.example.demo.test;

/**
 * 异常捕获处理
 */
public class ExceptionTest {

    public static void main(String[] args) {

        try{
            int i = subtraction();
            System.out.println(i);
        }catch (Exception e){
            System.out.println("异常 Exception 被捕获 异常信息为:"+e);
            e.printStackTrace();
        }

        System.out.println("-------------------------------");

        try{
            int a = 100 / 0;
        }catch (RuntimeException e){
            System.out.println("运行异常 RuntimeException 异常信息为:"+e);
            throw new RuntimeException(e);
        }catch (Exception e){
            System.out.println("运行异常 Exception 异常信息为："+e);
        }


    }

    public static int subtraction(){
        int result = 0;
        try {
             result = 10 / 0;
        }catch (ArithmeticException e){
            System.out.println("运行异常 RuntimeException 被捕获 异常信息"+e);
            throw new RuntimeException("运行异常");
        }
        return result;
    }

}
