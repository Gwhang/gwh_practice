/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis;


import org.apache.ibatis.domain.blog.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class MybatisMain {

  public static void main(String[] args) throws IOException {
    String resource = "mybatis-config.xml";//全局配置
    InputStream inputStream = Resources.getResourceAsStream(resource);//获取文件内容
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//获取SqlSessionFactory工厂
    //获取 SqlSession
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //执行查询语句
    HashMap<String,String> blog=sqlSession.selectOne("org.mybatis.example.BlogMapper.selectBlog", 1);
    //输出结果
    System.out.println(blog);

  }
}
