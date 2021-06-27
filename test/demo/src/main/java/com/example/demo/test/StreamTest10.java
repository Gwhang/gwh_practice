package com.example.demo.test;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * stream filter 增强判断
 *
 * 参数校验 校验图片名称和url 是否都填写 名称是否包含2
 *
 */
public class StreamTest10 {

    public static void main(String[] args) {

        List<FileInfo> fileInfoList = getFileInfoList();

        // 如果count 大于 0 则说明有为空的数据 校验不予通过
        long count = fileInfoList.stream().filter(p -> {
          return  StringUtils.isEmpty(p.getUrl()) || StringUtils.isEmpty(p.getFileName()) || p.getFileName().contains("2");
        }).count();

        System.out.println(count>0);


    }

    public static List<FileInfo> getFileInfoList(){

        List<FileInfo> fileInfos=new ArrayList<FileInfo>();

        FileInfo fileInfo1= FileInfo.builder().url("http://www.com").type("image").build();
        FileInfo fileInfo2= FileInfo.builder().fileName("222").url("http://www.com").type("image").build();
        FileInfo fileInfo3= FileInfo.builder().fileName("333").url("http://www.com").type("id").build();
        FileInfo fileInfo4= FileInfo.builder().fileName("444").url("http://www.com").type("image").build();

        fileInfos.add(fileInfo1);
        fileInfos.add(fileInfo2);
        fileInfos.add(fileInfo3);
        fileInfos.add(fileInfo4);

        return fileInfos;
    }


}


@Data
@Builder
class FileInfo{

    private String fileName;

    private String url;

    private String type;


}
