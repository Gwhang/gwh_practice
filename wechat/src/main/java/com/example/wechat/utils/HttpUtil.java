package com.example.wechat.utils;

import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName HttpUtil
 * @Description:发送http请求
 * @Author guanWanHang
 * @Date 2020/7/26
 * @Version V1.0
 **/
public class HttpUtil {

    public static String get(String url){
        try {
            URL urlObj=new URL(url);
            //开链接
            URLConnection connection=urlObj.openConnection();
            InputStream is =connection.getInputStream();
            byte[] b = new  byte[1024];
            int len;
            StringBuilder sb=new StringBuilder();
            while ((len=is.read(b))!=-1){
               sb.append(new String(b,0,len));
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
    *@MethodName: post
    *@Description: 向指定地址发送post请求
    *@Param: * @param url:
    * @param data:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/8/3
    **/
    public static String post(String url,String data){
        try {
            URL urlObj=new URL(url);
            URLConnection connection=urlObj.openConnection();
            //要发送请求，必须要设置可发送数据状态
            connection.setDoOutput(true);
            //获取输出流
            OutputStream os=connection.getOutputStream();
            //写数据
            os.write(data.getBytes());
            //关闭输出流
            os.close();
            InputStream is =connection.getInputStream();
            byte[] b = new  byte[1024];
            int len;
            StringBuilder sb=new StringBuilder();
            while ((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求表单
     * @param url
     * @param file
     * @return
     */
    public static String postForm(String url,File file){
        try{
            URL urlObj=new URL(url);
            //强制转换为安全连接
            HttpURLConnection conn=(HttpURLConnection)urlObj.openConnection();
            //设置连接的信息 允许输入输出
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            //设置请求头信息
            conn.setRequestProperty("Connection","Keep-Alive");
            conn.setRequestProperty("Charset","utf8");
            //数据边界
            String boundary="-----"+System.currentTimeMillis();
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            //获取输出流
            OutputStream out=conn.getOutputStream();
            //创建文件的输入流
            InputStream is =new FileInputStream(file);
            //第一部分头部信息
            //准备头部信息
            StringBuilder sb =new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+file.getName()+"\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            System.out.println(sb.toString());
            out.write(sb.toString().getBytes());
            //第二部分：文件内容
            byte[] b = new byte[1024];
            int len;
            while ((len=is.read(b))!=-1){
                out.write(b,0,len);
            }
            //第三部分 尾部信息
            String foot="\r\n--"+boundary+"--\r\n";
            out.write(foot.getBytes());
            out.flush();
            out.close();
            //读取信息
            InputStream is2=conn.getInputStream();
            StringBuilder resp=new StringBuilder();
            while((len=is2.read(b))!=-1){
                resp.append(new String(b,0,len));
            }
            return resp.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
