package com.example.wechat.service;

import com.example.wechat.utils.HttpUtil;
import com.example.wechat.utils.SendMsg;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 素材上传
 */
@Service
public class UploadService {


    /**
     * 上传临时素材
     * @param path 上传文件的路径
     * @param type 上传文件的类型
     * @return
     */
    public static String upload(String path,String type){
        File file=new File(path);
        String token=WechatService.getAccessToken();
        String url= SendMsg.UPLOAD.replace("ACCESS_TOKEN",token).replace("TYPE",type);
        String result= HttpUtil.postForm(url,file);
        System.out.println(result);
        return result;
    }

}
