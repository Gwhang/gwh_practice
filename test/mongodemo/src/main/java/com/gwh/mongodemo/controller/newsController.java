package com.gwh.mongodemo.controller;

import com.gwh.mongodemo.pojo.News;
import com.gwh.mongodemo.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * MongoDB实现一个新闻得缓存功能，实现缓存之前，要清楚缓存的核心作用：
 * 提升web程序的查询速度，将热点数据放到非关系数据库中。
 */
@Controller
public class newsController {

    Logger logger= LoggerFactory.getLogger(newsController.class);

    @Autowired
    private NewsService newsService;

    /**
     * 给用户返回该标题新闻
     * @param title
     * @return
     */
    @GetMapping("getnews/{title}")
    @ResponseBody
    public News getnews(@PathVariable String title)
    {
        News news=newsService.getNewsByTitle(title);
        return news;
    }

    /**
     * 更新缓存
     * @param title 标题
     * @param content 内容
     * @return
     */
    @GetMapping("/updatenews")
    @ResponseBody
    public String updatenews(String title,String content){
        boolean falg = newsService.updateNewsContentByTitle(title,content);
        return "更新结果为:"+falg;
    }

    @GetMapping("deletenews/{title}")
    @ResponseBody
    public String deletenews(@PathVariable String title){
        boolean flag=newsService.deleteNewsByTitle(title);
        return "更新结果为:"+flag;
    }



}
