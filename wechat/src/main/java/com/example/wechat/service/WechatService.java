package com.example.wechat.service;

import com.baidu.aip.ocr.AipOcr;
import com.example.wechat.entity.*;
import com.example.wechat.utils.HttpUtil;
import com.example.wechat.utils.JuheDemoUtil;
import com.example.wechat.utils.SendMsg;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName WechatService
 * @Description:微信业务类
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
@Service
public class WechatService {
    //静态类无法读取配置文件，后期可加入到redis中
    //@Value("${wechat.token}")
    private static String token="test";
   // @Value("${wechat.appID}")
    private static String appId="wx5f2bf083338005c6";
   // @Value("${wechat.appsecret}")
    private static String appsecret="c901f92c34142ef9946545969cafbbc0";
    private static AccessToken at;

    //配置您申请的KEY 自动聊天机器人
    public static final String APPKEY ="*************************";
   //百度
    public static final String APP_ID="22310170";
    public static final String APP_KEY="IRz0iM43Ab9bVSXwoNU4mBiv";
    public static final String SECRET_KEY="Yd8bNNtUDmf8CIaZTgKCW2hM8Sw7oeTc";
    /**
    *@MethodName: getToken
    *@Description: 获取token
    *@Param:
    *@Return: * @return: void
    *@Author: guanwanhang
    *@Date: 2020/7/26
    **/
   public static void getToken(){
       //替换token中的appid和appsecret
       String url=SendMsg.ACCESS_TOKEN.replace("APPID",appId).replace("APPSECRET",appsecret);
       String result= HttpUtil.get(url);
       System.out.println(result);
       JSONObject json=JSONObject.fromObject(result);
       String accessToken=json.getString("access_token");
       String expiresIn=json.getString("expires_in");
       //创建token对象，并存起来
       at=new AccessToken(accessToken,expiresIn);
   }

   /**
   *@MethodName: getAccessToken
   *@Description: 获取accessToken
   *@Param:
   *@Return: * @return: java.lang.String
   *@Author: guanwanhang
   *@Date: 2020/7/26
   **/
   public static String getAccessToken(){
       if(at==null || at.isExpired()){
           getToken();
       }
      return at.getAccessToken();
   }

    /**
    *@MethodName: check
    *@Description: 验证签名
    *@Param: * @param timestamp:
     * @param nonce:
    *@Return: * @return: boolean
    *@Author: guanwanhang
    *@Date: 2020/7/25
    **/
    public boolean check(String timestamp,String nonce,String signature){
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs=new String[]{token,timestamp,nonce};
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str=strs[0]+strs[1]+strs[2];
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String mysig=sha1(str);
//        System.out.println(mysig);
//        System.out.println(signature);
        return mysig.equals(signature);
    }
    /**
    *@MethodName: sha1
    *@Description: sha1加密
    *@Param: * @param src:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/7/25
    **/
    private static String sha1(String src){
        try {
            //获取一个加密对象
            MessageDigest md=MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb=new StringBuilder();
            //处理加密结果
            for (byte b:digest){
               sb.append(chars[(b>>4)&15]);
               sb.append(chars[b&15]);
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
    *@MethodName: parseRequest
    *@Description: 解析XML数据包
    *@Param: * @param request:
    *@Return: * @return: java.util.Map<java.lang.String,java.lang.String>
    *@Author: guanwanhang
    *@Date: 2020/7/25
    **/
    public static Map<String,String> parseRequest(InputStream is){
        HashMap<String,String> map=new HashMap<>();
        SAXReader reader=new SAXReader();
        try {
            //读取输入流，获取文档对象
            Document read = reader.read(is);
            //根据文档对象获取根节点
            Element root=read.getRootElement();
            //获取根节点的所有子节点
            List<Element> elements = root.elements();
            for (Element e:elements){
                map.put(e.getName(),e.getText());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
    *@MethodName: getRespose
    *@Description: 用于处理所有的事件和消息的回复
    *@Param: * @param resultMap:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/7/25
    **/
    public static String getRespose(Map<String,String> resultMap){
        BaseMessage msg=null;
        switch (resultMap.get("MsgType")){
            case SendMsg.TEXT:
                msg=dealTextMessage(resultMap);
                break;
            case SendMsg.IMAGE:
                    msg = dealImage(resultMap);
                break;
            case SendMsg.VOICE:

                break;
            case SendMsg.VIDEO:

                break;
            case SendMsg.MUSIC:

                break;
            case SendMsg.NEWS:

                break;
            case SendMsg.EVENT:
                msg=delalEvent(resultMap);
              break;
        }
        if(msg!=null){
        //把消息对象处理为XML数据包
        return beanToXml(msg);
        }else {
            return null;
        }
    }
    /**
    *@MethodName: dealTextMessage
    *@Description: 处理文本消息回复
    *@Param: * @param resultMap:
    *@Return: * @return: com.example.wechat.entity.BaseMessage
    *@Author: guanwanhang
    *@Date: 2020/7/26
    **/
    public static BaseMessage dealTextMessage(Map<String,String> resultMap){
        //用户发来的消息
        String msg = resultMap.get("Content");
        //调用方法返回聊天的内容
       // String resp=chat(msg);
        //图文消息处理
        if(msg.equals("图文")){
            List<Article> articles= Lists.newArrayList();
            articles.add(new Article("这是图文消息的标题","这是图文消息的详细介绍","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595784869771&di=b2faa890b951cf3155aa3b8e3c40553f&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2F1ecd37cc8dbdc59b5a082c7f190e7f107b051f3e.jpg","http://www.baidu.com"));
            NewsMessage newsMessage=new NewsMessage(resultMap,1,articles);
            return newsMessage;
        }
        TextMessag tm=new TextMessag(resultMap,"处理成功");
        return tm;
    }

    /**
    *@MethodName: chat
    *@Description: 调用聊天机器人  https://code.juhe.cn/docs/834
    *@Param: * @param msg:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/7/26
    **/
    public static String chat(String msg){
        String result =null;
        String url ="http://op.juhe.cn/robot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info",msg);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
        params.put("loc","");//地点，如北京中关村
        params.put("lon","");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat","");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联

        try {
            result = JuheDemoUtil.net(url, params, "GET");
            //解析json
            JSONObject object = JSONObject.fromObject(result);
            //判断是否出错 不为0就是异常
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
            return object.getJSONObject("result").getString("text");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    *@MethodName: beanToXml
    *@Description: 把消息处理为xml数据包
    *@Param: * @param msg:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/7/26
    **/
    public static String beanToXml(BaseMessage msg){
        XStream stream=new XStream();
        //设置需要处理@XStreamAlias("xml") 注释的类
        stream.processAnnotations(TextMessag.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(MusicMessage.class);
        String xml=stream.toXML(msg);
        return xml;
    }


    /**
    *@MethodName: delalEvent
    *@Description: 处理事件推送
    *@Param: * @param resultMap:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/8/4
    **/
    public static BaseMessage delalEvent(Map<String,String> resultMap){
        String event = resultMap.get("Event");
        BaseMessage msg=new BaseMessage();
        switch (event){
            case SendMsg.EVENT_CLICK:
                msg=dealClick(resultMap);
               break;
            case SendMsg.EVENT_VIEW:
                msg=dealView(resultMap);
                break;
            case SendMsg.SUBSCRIBE:
                msg = dealSubscribe(resultMap);
                break;
        }
        return msg;
    }

    /**
    *@MethodName: dealClick
    *@Description: 点击事件推送
    *@Param: * @param resultMap:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/8/4
    **/
    public static BaseMessage dealClick(Map<String,String> resultMap){
        //事件KEY值，与自定义菜单接口中KEY值对应
        String key=resultMap.get("EventKey");
        BaseMessage msg=new BaseMessage();
        switch (key){
            //点击一级菜单
            case "1":
                msg= new TextMessag(resultMap,"你点了一下");
               break;
            case "32":
                msg= new TextMessag(resultMap,"哒哒哒！！！");
               break;
        }
        return msg;
    }

    /**
    *@MethodName: dealView
    *@Description: 跳转事件推送
    *@Param: * @param resultMap:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/8/4
    **/
    public static BaseMessage dealView(Map<String,String> resultMap){

        return null;
    }

    /**
     * https://ai.baidu.com/ai-doc/OCR/Dk3h7ybtj
     * 进行图片识别
     * @param resultMap
     * @return
     */
    public static BaseMessage dealImage(Map<String,String> resultMap){
        //初始化对象
        AipOcr aipocr=new AipOcr(APP_ID,APP_KEY,SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("language_type", "CHN_ENG");
//        options.put("detect_direction", "true");
//        options.put("detect_language", "true");
//        options.put("probability", "true");
        // 参数为本地图片路径
        String image = resultMap.get("PicUrl");
        //调用接口 远程接口basicGeneralUrl
        org.json.JSONObject res = aipocr.basicGeneralUrl(image, options);
        String json =res.toString();
        //转换未jsonObject
        JSONObject jsonObject = JSONObject.fromObject(json);
        //获取文字对象
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        //遍历array
        Iterator<JSONObject> iterator = words_result.iterator();
        //拼接字符串
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            //获取返回结果
            JSONObject next= iterator.next();
            sb.append(next.getString("words"));
        }
        //返回文字消息
        return new TextMessag(resultMap,sb.toString());
    }

    public static BaseMessage dealSubscribe(Map<String,String> resultMap){
        //用户发来的消息
        String msg = resultMap.get("Content");
        //调用方法返回聊天的内容
        // String resp=chat(msg);
        TextMessag tm=new TextMessag(resultMap,resultMap.get("EventKey"));
        return tm;

    }


}
