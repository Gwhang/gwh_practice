package com.gwh.mongodemo.pojo;

import java.util.Date;

public class News {
    //标题
    private String title;
    //日期
    private Date date;
    //概要
    private String brief;
    //内容
    private String content;
    //作者
    private String author;

    public News(String title, Date date, String brief, String content, String author) {
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
        this.author = author;
    }
    @Override
    public String toString() {
        return "news{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
