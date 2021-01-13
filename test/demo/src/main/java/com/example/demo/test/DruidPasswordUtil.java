package com.example.demo.test;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;

/**
 * 数据库密码解密
 */
public class DruidPasswordUtil extends DruidPasswordCallback {

    public static void main(String[] args) throws Exception{
        //密码明文
        String password = "123456";
        //获取公私钥
        String [] keyPair = ConfigTools.genKeyPair(512);
        String privateKey = keyPair[0];
        String publicKey = keyPair[1];

        System.out.println("privateKey:"+privateKey);
        System.out.println("publicKey:"+publicKey);
        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);
        System.out.println("私钥加密后===:"+password);
        //用公钥加密后的密文
        String decryptPassword=ConfigTools.decrypt(publicKey, password);
        System.out.println("公钥解密后===："+decryptPassword);


        //用公钥加密后的密文
        String decryptPassword1=ConfigTools.decrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKrzpMGAe9YBWG+/Gebu7QuZ71LQFAmfR/ezXq9LRYXvh9/pvthDlLxYdT7wz9D7+LTJx41bgjhLjuEGsNp7Nx8CAwEAAQ==", "mDIoam8UGU+nKzRFBVM1n/CsyfAbHBe0UuzS7QkyzFZe8z+y8vQazP27HF9HtnBU+npszpMwxU4Qb70QelPfZw==");
        System.out.println("公钥解密后===："+decryptPassword1);

    }


}
