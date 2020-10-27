SpringBoot发送邮件
使用的场景：
注册验证
网站营销
安全的最后一道防线
提醒、监控告警
触发机制
发送邮件的原理：
邮件传输协议：SMTP协议和POP3协议 IMAP协议和MIME协议
发送邮件流程
发信人在邮件服务器上编辑邮件，在邮件地址上写清楚发送地址，编辑邮件为符合格式的邮件
用户代理把邮件发送到发信人的邮件服务器上，邮件服务器上有一个队列，所有发送过来的邮件
都会存在这个缓存队列中等到服务器上的SMTP客户端进行发送，发送人的邮件通过SMTP协议发
送到收件人的邮件服务器中，收件人的邮件服务器接收到这封邮件以后，把邮件放到收件人的信箱中
收件人通过用户代理来收取邮件，通过POP3来连接收件人所在的邮件服务器，连接成功后，用户代理
把邮件展示给收件人
Spring Boot的理解
针对为什么要使用Spring Boot？SpringBoot有哪些优点？
良好的基因
Spring Boot 是伴随着 Spring 4.0 诞生的，从字面理解，Boot 是引导的意思，
因此 Spring Boot 旨在帮助开发者快速搭建 Spring 框架。Spring Boot 继承了原有
 Spring 框架的优秀基因，使 Spring 在使用中更加方便快捷
 简化编码
 举个例子，比如我们要创建一个 Web 项目，使用 Spring 的朋友都知道，在使用 Spring 的时候，需要在 pom 文件中添加多个依赖，
 而 Spring Boot 则会帮助开发者快速启动一个 Web 容器，在 Spring Boot 中，我们只需要在 pom 文件中添加如下一个 starter-web 依赖即可。
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
 </dependency>
 我们点击进入该依赖后可以看到，Spring Boot 的这个 starter-web 已经包含了多个依赖，包括之前在 Spring 工程中需要导入的依赖，
 我们看一下其中的一部分，如下：
 <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-web</artifactId>
     <version>5.0.7.RELEASE</version>
     <scope>compile</scope>
 </dependency>
 <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-webmvc</artifactId>
     <version>5.0.7.RELEASE</version>
     <scope>compile</scope>
 </dependency>
 由此可以看出，Spring Boot 大大简化了我们的编码，我们不用一个个导入依赖，直接一个依赖即可。
 简化配置
 Spring 虽然是 Java EE 轻量级框架，但由于其繁琐的配置，一度被人认为是“配置地狱”。
 各种 XML、Annotation 配置让人眼花缭乱，而且配置多的话，如果出错了也很难找出原因。Spring Boot 更多的是采用 Java Config 的方式，对 Spring 进行配置。下面看个例子。
 我新建一个类，但是我不用 @Service 注解，也就是说，它是个普通的类，那么我们如何使它也成为一个 Bean 让 Spring 去管理呢？只需要 @Configuration 和 @Bean 两个注解即可，如下：
 public class TestService {
     public String sayHello () {
         return "Hello Spring Boot!";
     }
 }
 
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 
 @Configuration
 public class JavaConfig {
     @Bean
     public TestService getTestService() {
         return new TestService();
     }
 }
 
 @Configuration 表示该类是个配置类，@Bean 表示该方法返回一个 Bean。这样就把 TestService 作为 Bean 让 Spring 去管理了，
 在其他地方，我们如果需要使用该 Bean，和原来一样，直接使用 @Resource 注解注入进来即可使用，非常方便。
另外，部署配置方面，原来 Spring 有多个 XML 和 Properties 配置，在 Spring Boot 中只需要一个 application.yml 即可。

简化部署
使用 Spring 时，项目部署时需要我们在服务器上部署 Tomcat，然后把项目打成 War 包扔到 Tomcat 里。使用 Spring Boot 后，
我们不需要在服务器上去部署 Tomcat，因为 Spring Boot 内嵌了 Tomcat，我们只需要将项目打成 Jar 包，使用 java -jar xxx.jar 一键式启动项目。
简化监控
我们可以引入 spring-boot-start-actuator 依赖，直接使用 REST 方式来获取进程的运行期性能参数，从而达到监控的目的，比较方便。
但是 Spring Boot 只是个微框架，没有提供相应的服务发现与注册的配套功能，没有外围监控集成方案，没有外围安全管理方案，所以在微服务架构中，还需要 Spring Cloud 来配合一起使用。
从未来发展的趋势来看
微服务是未来发展的趋势，项目会从传统架构慢慢转向微服务架构，因为微服务可以使不同的团队专注于更小范围的工作职责、使用独立的技术、更安全更频繁地部署。而继承了 Spring 的优良特性，
与 Spring 一脉相承，而且支持各种 REST API 的实现方式。Spring Boot 也是官方大力推荐的技术，可以看出，Spring Boot 是未来发展的一个大趋势。




