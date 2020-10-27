package com.example.wechat.service;

import org.junit.Test;

/**
 * 素材上传测试类
 */
public class UploadServiceTest {

    private UploadService uploadService;

    @Test
    public void TestUpload(){
        String file="D:\\1.png";
        uploadService.upload(file,"image");
    }

}
