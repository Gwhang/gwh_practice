package com.example.wechat.entity;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsMessage
 * @Description: 图文消息
 * @Author guanWanHang
 * @Date 2020/7/26
 * @Version V1.0
 **/
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{
    @XStreamAlias("ArticleCount")
    private long articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles= Lists.newArrayList();

    public long getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(long articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public NewsMessage(){

    }

    public NewsMessage(Map<String,String> requestMap, long articleCount,List<Article> articles){
        super(requestMap);
        this.setMsgType("news");
        this.articleCount=articleCount;
        this.articles=articles;

    }



}
