package com.gwh.mongodemo.service;

import com.gwh.mongodemo.pojo.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 业务层
 */
@Service
public class NewsService {

    Logger logger= LoggerFactory.getLogger(NewsService.class);

    //注入MongoDB服务
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据标题返回缓存或数据库中该条news数据，如果MongoDB中存在则直接返回该对象，否则先从数据库查询(这里直接生成)，
     * 然后存到MongoDB中再返回
     * 我们核心使用mongoTemplate对象来实现查询一条记录，查询语句为：mongoTemplate.findOne(query, news.class)，第一个参数为查询的条件，第二个参数为查询结果转成Java对象的类型，它帮你自动处理。
     * 通过Query对象来辅助我们实现条件查询，这里的意思就是查询条件为：MongoDB中title字段为传进来title字符串的该条记录。
     * 而插入的语法为 mongoTemplate.insert(news1,“news”)，第一个参数为插入的文档记录，第二个参数为连接呃MongoDB对应数据库下的集合(Collections)。
     * @param title
     * @return
     */
    public News getNewsByTitle(String title){
        //查询数据先从MongoDB中查询
        Query query=new Query(Criteria.where("title").is(title));
        News news = mongoTemplate.findOne(query,News.class);
        //判断如果mongoDB中不存在数据 查询关系型数据库
        if (StringUtils.isEmpty(news)){
            logger.info("缓存中不存在数据，从数据库查询");
            //假设news1从数据库中查询
            News news1=new News(title,new Date(),"","","bigsai");
            news1.setBrief("有了博学谷，妈妈再也不用担心我的java学习！");
            news1.setContent("博学谷优质学习资料为java学习提供更好环境,越来越多开发者学习使用");
            mongoTemplate.insert(news1,"news");
            logger.info("数据插入到MongoDB成功");
            news=news1;
        }else {
            logger.info("访问缓存成功");
        }
        return news;
    }

    /**
     * 缓存更新
     *Query对象来辅助我们实现条件查询待更新数据，这里的意思就是查询条件同样为：MongoDB中title字段为传进来title字符串的该条记录。
     * Update对象用来记录更新的字段和数据，这里更新传进来的content内容和date日期。
     * mongoTemplate.upsert(query, update, news.class)用来实现更新，如果MongoDB中不存在该数据那么就插入到MongoDB中。
     * @param title 标题
     * @param content 更新内容
     * @return
     */
    public boolean updateNewsContentByTitle(String title,String content){
        try {
            //查询标题数据
            Query query = new Query(Criteria.where("title").is(title));
            Update update = new Update();
            update.set("content",content);//更新内容
            update.set("date",new Date());//更新时间
            // 假设在这里数据库中更新过这里跳过

            // updateFirst 更新查询返回结果集的第一条
            //upsert 更新如果不存在就插入
            mongoTemplate.upsert(query, update, News.class);
        }catch (Exception e){
            return false;
        }
      return true;
    }

    /**
     * 删除缓存
     * mongoTemplate.remove(query,news.class);意味从MongoDB中删除满足查询条件的记录。
     * 其中query为查询条件，news.class为删除对象在Java中的类。
     *
     * @param title
     * @return
     */
    public boolean deleteNewsByTitle(String title){
        try {

            Query query = new Query(Criteria.where("title").is(title));
            mongoTemplate.remove(query,News.class);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
