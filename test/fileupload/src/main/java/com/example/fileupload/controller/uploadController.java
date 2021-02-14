package com.example.fileupload.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 前端就是form表单用<input type="file">表示客户端要上传文件，
 * 而服务端主要使用MultipartFile或者MultipartFile[]分别接收单个文件和多个文件。
 * 而在存储到本地也仅仅需要在本地磁盘创建对应文件然后MultipartFile调用transferTo()方法即可将上传的文件储存
 *
 */
@Controller
public class uploadController {

    /**
     * 单文件上传
     * 前台form表签：enctype要为multipart/form-data类型，表示二进制传输。
     * @param file 文件流
     * @return
     * @throws IOException
     */
    @PostMapping("onfile")
    @ResponseBody
    public String onfile(MultipartFile file) throws IOException {
        File file1 =new File("D:/fileupload/"+file.getOriginalFilename());//创建file对象
        if(!file1.exists())
            file1.createNewFile();//在磁盘创建该文件
        file.transferTo(file1);//将接受的文件存储
        return "success";
    }

    /**
     * 多文件上传
     * MultipartFile[]对每个文件进行接收处理，当然文件为空的时候不进行处理。
     * @param img
     * @return
     * @throws IOException
     */
    @PostMapping("onfiles2")
    @ResponseBody
    public String onfiles2(MultipartFile img[]) throws IOException {
        for(int i=0;i<img.length;i++)
        {
            if(!img[i].isEmpty())//文件不空
            {
                File imgfile =new File("D:/fileupload/"+img[i].getOriginalFilename());
                imgfile.createNewFile();
                img[i].transferTo(imgfile);
            }
        }
        return "success";
    }

    /**
     * 文件下载
     * @param filename
     * @return
     * @throws IOException
     */
    @GetMapping("download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) throws IOException {
        //下载文件的路径(这里绝对路径)
        String filepath= "D:/fileupload/"+filename;
        File file =new File(filepath);
        //创建字节输入流，这里不实用Buffer类
        InputStream in = new FileInputStream(file);
        //available:获取输入流所读取的文件的最大字节数
        byte[] body = new byte[in.available()];
        //把字节读取到数组中
        in.read(body);
        //设置请求头
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置响应状态
        HttpStatus statusCode = HttpStatus.OK;
        in.close();
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;//返回
    }

    /**
     * 混合上传 单文件+多文件
     * 这个和上面主要的区别就是函数中的多参数，其实每一个参数都是要和前端页面的form表单input标签的内容对应(名称一致)。
     * form表单中的file类型在Springmvc的controller中就是对应MultipartFile类型，
     * form表单中的text类型对应controller中的String类型。如果上传单个文件，
     * 在服务端就用MultipartFile类型参数接收，如果多文件就用MultipartFile[]进行接收
     * @param name
     * @param age
     * @param img
     * @param resume
     * @return
     * @throws IOException
     */
    @PostMapping("infoupload")
    @ResponseBody
    public String onfile(String name,String age, MultipartFile img[],MultipartFile resume) throws IOException {
        //接收img[]
        for(int i=0;i<img.length;i++)
        {
            if(!img[i].isEmpty())//文件不空
            {
                File imgfile =new File("D:/fileupload/"+img[i].getOriginalFilename());
                imgfile.createNewFile();
                img[i].transferTo(imgfile);
            }
        }
        //接收resume
        File resumefile =new File("D:/fileupload/"+resume.getOriginalFilename());
        //在磁盘中创建文件，此时文件存在但没有内容
        resumefile.createNewFile();
        //将接受的文件复制到创建的文件中
        resume.transferTo(resumefile);
        return "success";
    }

}
