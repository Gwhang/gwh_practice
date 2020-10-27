package com.example.wechatpay.util;

import com.alibaba.fastjson.JSON;
import com.example.wechatpay.enums.BusinessCodeEnum;
import com.example.wechatpay.pojo.RefundInfo;
import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.jdom.Element;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.*;

/**
 * 微信支付工具类
 */
public class WechatPayUtils {

   static Logger logger= LoggerFactory.getLogger(WechatPayUtils.class);

    // JSAPI支付
    public static final String WECHAT_JSAPI = "JSAPI";
    // H5支付
    public static final String WECHAT_MWEB = "MWEB";

    //微信退款
    public static final String WECHAT_RETUND = "RETUND";
    //微信付款查询
    public static final String WECHAT_PAY_QUERY = "PAYQUERY";
    //微信退款查询
    public static final String WECHAT_RETUND_QUERY = "RETUNDQUEY";

    //微信回调签名key
    private static final String FIELD_SIGN = "sign";

    /*
     * 发起支付请求 body 商品描述 out_trade_no 订单号 total_fee 订单金额 单位 元 product_id 商品ID
     * appid：微信支付分配的公众账号ID（企业号corpid即为此appId）
     * mchid：微信支付分配的商户号
     * openid：rade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
     * notify_url：异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
     * sign：通过签名算法计算得出的签名值
     * trade_type：交易类型
     * spbill_create_ip：支持IPV4和IPV6两种格式的IP地址。用户的客户端IP
     */
    public static Map<String, String> sendPayment(String body,
                                                  String out_trade_no,
                                                  BigDecimal total_fee,
                                                  String ip,
                                                  String trade_type,
                                                  String openId,
                                                  String wechatNotifyUrl,
                                                  String wechatAppId,
                                                  String wechatMchId,
                                                  String wechatPlatformAppId,
                                                  String wechatPlatformMchId,
                                                  String wechatPlatformApiKey,
                                                  String weChatPrepayUrl) {
        String xml = wXParamGenerate(body, out_trade_no, total_fee, ip, trade_type, openId,
                wechatNotifyUrl, wechatAppId, wechatMchId, wechatPlatformAppId, wechatPlatformMchId, wechatPlatformApiKey);
        String res = httpsRequest(weChatPrepayUrl, "POST", xml);

        Map<String, String> data = null;
        try {
            data = doXMLParse(res);
        } catch (Exception e) {
        }
        return data;
    }


    // 微信统一下单参数设置
    private static String wXParamGenerate(String description,
                                          String outTradeNo,
                                          BigDecimal total_fee,
                                          String ip,
                                          String trade_type,
                                          String openId,
                                          String wechatNotifyUrl,
                                          String wechatAppId,
                                          String wechatMchId,
                                          String wechatPlatformAppId,
                                          String wechatPlatformMchId,
                                          String wechatPlatformApiKey
    ) {
        int fee = total_fee.multiply(new BigDecimal("100"))
                .setScale(0, BigDecimal.ROUND_HALF_UP)
                .intValue();
        Map<String, String> param = new HashMap<String, String>();
        param.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
        param.put("body", description);
        param.put("out_trade_no", outTradeNo);
        param.put("total_fee", fee + "");
        param.put("sign_type", "MD5");
        param.put("notify_url", wechatNotifyUrl);
        // 交易类型
        param.put("trade_type", trade_type);
        if (WECHAT_JSAPI.equals(trade_type)) {
            param.put("openid", openId);
            param.put("appid", wechatAppId);
            param.put("mch_id", wechatMchId);
            param.put("spbill_create_ip", ip);
        } else {
            param.put("appid", wechatPlatformAppId);
            param.put("mch_id", wechatPlatformMchId);
            if (WECHAT_MWEB.equals(trade_type)) {
                param.put("spbill_create_ip", ip);
            }
        }
        param.put(
                "scene_info",
                "{\"h5_info\":{\"type\":\"Wap\",\"wap_url\":\"http://" + "www.xueguoxue.com" + "\",\"wap_name\":\"您正在购买商品\"}}");
        logger.info("验签参数集合 {}", JSON.toJSONString(param));
        String sign = getSign(param, wechatPlatformApiKey);
        param.put("sign", sign);
        return getMapToXML(param);
    }

    private static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

    /**
     * @return java.lang.String
     * @Author liushan
     * @Description Map转xml数据
     * @Date 2018/11/29 16:20
     * @Param [param]
     **/
    public static String getMapToXML(Map<String, String> param) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append("<" + entry.getKey() + ">");
            sb.append(entry.getValue());
            sb.append("</" + entry.getKey() + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }
    /**
     * @return java.lang.String
     * @Author liushan
     * @Description 微信回调返回参数
     * @Date 2018/11/29 16:18
     * @Param [return_code, return_msg]
     **/
    public static String setXML(HashMap<String,String> return_data) {
        String returnCode = return_data.get("return_code");
        String returnMsg = return_data.get("return_msg");
        return "<xml><return_code><![CDATA[" + returnCode
                + "]]></return_code><return_msg><![CDATA[" + returnMsg
                + "]]></return_msg></xml>";
    }
    /**
     * @return java.lang.String
     * @Author liushan
     * @Description 获取返回给微信支付的签名
     * @Date 2020年1月7日 17:24:37
     * @Param [weChatResult]
     **/
    public static String getPaySign(Map<String, String> weChatResult, String tradeType, String wechatPlatformApiKey) {
        String sign = "";
        Map<String, String> param = new HashMap<>();
        if (tradeType.equals(WECHAT_JSAPI)) {
            param.put("appId", weChatResult.get("appid"));
            param.put("nonceStr", weChatResult.get("nonce_str"));
            param.put("timeStamp", weChatResult.get("timestamp"));
            param.put("signType", weChatResult.get("sign_type"));
            param.put("package", "prepay_id=" + weChatResult.get("prepay_id"));
        } else {
            param.put("appid", weChatResult.get("appid"));
            param.put("partnerid", weChatResult.get("mch_id"));
            param.put("prepayid", weChatResult.get("prepay_id"));
            param.put("noncestr", weChatResult.get("nonce_str"));
            param.put("timestamp", weChatResult.get("timestamp"));
            param.put("package", weChatResult.get("packageInfo"));
        }
        sign = getSign(param, wechatPlatformApiKey);
        return sign;
    }
    /**
     * 微信退款服务方法
     * @return
     */
    public static Map<String, String> sendRefundPayment(RefundInfo refundInfo) {
        logger.info("微信原路返回入参 {}", JSON.toJSONString(refundInfo));
        String refundFee = String.valueOf(refundInfo.getRefundFee().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
        String totalFee = String.valueOf(refundInfo.getTotalFee().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());

        String xml = wXParam(refundInfo.getAppid(), refundInfo.getMchId(), refundInfo.getNotifyUrl(), refundFee, totalFee, refundInfo.getTransactionId(),
                refundInfo.getOutRefundNo(), refundInfo.getSign(),WECHAT_RETUND);

        //微信退款发送消息
        //加载证书
        String res = initCert(refundInfo.getNotifyUrl(), xml, refundInfo.getMchId());

        Map<String, String> data = null;
        try {
            data = doXMLParse(res);
        } catch (Exception e) {
        }
        return data;
    }

    /**
     * 微信退款查询结果
     */
    public static Map<String, String> queryRetundWeChatPay(String appid, String mchId, String serialNumber, String wechatPlatformApiKey,String weChatQueryRefundPrepayUrl) {
        String xml = wXParam(appid, mchId, "", "", "","" ,serialNumber,wechatPlatformApiKey,WECHAT_RETUND_QUERY);

        String res = httpsRequest(weChatQueryRefundPrepayUrl, "POST", xml);

        Map<String, String> data = null;
        try {
            data = doXMLParse(res);
        } catch (Exception e) {
        }
        return data;
    }
    // 微信退款/查询参数设置
    private static String wXParam(String appid, String mchId, String refundNotifyUrl, String refundFee,String totalFee,
                                  String payNumber, String outTradeNo, String wechatPlatformApiKey,String type) {

        Map<String,String> param = new HashMap<String,String>();
        //微信退款
        if (WECHAT_RETUND.equals(type)){
            param.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
            param.put("appid",appid);
            param.put("mch_id",mchId);
            param.put("notify_url",refundNotifyUrl);
            param.put("refund_fee",refundFee);
            param.put("total_fee",totalFee);
            param.put("transaction_id",payNumber);
            param.put("out_refund_no",outTradeNo);
        }//微信付款查询
        else if (WECHAT_PAY_QUERY.equals(type)){
            param.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
            param.put("appid",appid);
            param.put("mch_id",mchId);
            param.put("out_trade_no",outTradeNo);
//            param.put("transaction_id","4200000689202009029472000195");
        }//微信退款查询
        else if (WECHAT_RETUND_QUERY.equals(type)){
            param.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
            param.put("appid",appid);
            param.put("mch_id",mchId);
            param.put("out_refund_no",outTradeNo);
        }
        String sign = getSign(param, wechatPlatformApiKey);

        param.put("sign", sign);

        return getMapToXML(param);
    }

    // 发起微信支付请求
    private static String httpsRequest(String requestUrl, String requestMethod,
                                       String outputStr) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type",
                    "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}" + ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}" + e);
        }finally {
            // 释放资源
            try{
                if (Objects.nonNull(bufferedReader)){
                    bufferedReader.close();
                }
                if (Objects.nonNull(inputStreamReader)){
                    inputStreamReader.close();
                }
                if (Objects.nonNull(inputStream)){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Objects.nonNull(conn)){
                conn.disconnect();
            }
            inputStream = null;
        }
        return null;
    }

    // 组装参数
    public static Map<String, String> doXMLParse(String strxml)
            throws Exception {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map<String, String> m = new HashMap<String, String>();
        InputStream in = null;
        try{
            in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(in);
            //指向根节点
            Element root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String k = e.getName();
                String v = "";
                List children = e.getChildren();
                if (children.isEmpty()) {
                    v = e.getTextNormalize();
                } else {
                    v = getChildrenText(children);
                }

                m.put(k, v);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (Objects.nonNull(in)){
                // 关闭流
                in.close();
            }
        }
        return m;
    }

    /**
     * @Author liushan
     * @Description //TODO 获取
     * @Date 2020/1/3 15:26
     * @Param [param, wechatPlatformApiKey]
     * @return java.lang.String
     **/
    public static String getSign(Map<String, String> param, String wechatPlatformApiKey) {
        logger.info("param {}",JSON.toJSONString(param));
        String paramUrl = formatUrlMap(param, true, false);
        logger.info("StringA {}",paramUrl);
//        if (paramUrl!=null){
//            return null;
//        }
        String stringSignTemp = MD5Utils.md5(java.net.URLDecoder.decode(paramUrl) + "&key=" + wechatPlatformApiKey)
                .toUpperCase();
        return stringSignTemp;
    }

    /**
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写 true:key转化成小写，false:不转化
     * @return
     */
    private static String formatUrlMap(Map<String, String> paraMap,
                                       boolean urlEncode, boolean keyToLower) {
        if (paraMap == null) {
            return "";
        }
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                    tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds,
                    new Comparator<Map.Entry<String, String>>() {
                        @Override
                        public int compare(Map.Entry<String, String> o1,
                                           Map.Entry<String, String> o2) {
                            return (o1.getKey()).toString().compareTo(
                                    o2.getKey());
                        }
                    });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                String key = item.getKey();
                String val = item.getValue();
                if (urlEncode) {
                    val = URLEncoder.encode(val, "utf-8");
                }
                if (keyToLower) {
                    buf.append(key.toLowerCase() + "=" + val);
                } else {
                    buf.append(key + "=" + val);
                }
                buf.append("&");
            }
            buff = buf.toString();
            if (buff.equals("") == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }



    //加载证书
    private static String initCert(String url,String xml,String mchId) {
        RestTemplate restTemplate = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");//eg. PKCS12
            ClassPathResource cp = new ClassPathResource("apiclient_cert.p12");
            keyStore.load(cp.getInputStream(), mchId.toCharArray());

            SSLContext sslcontext = SSLContextBuilder.create()
                    .loadKeyMaterial(keyStore, mchId.toCharArray())
                    .build();

//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, NoopHostnameVerifier.INSTANCE);
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
//            restTemplate = new RestTemplate(factory);
//            //将转换器的编码换成utf-8
//            restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("utf-8")));


            try {
                HttpPost httpost = new HttpPost(url); // 设置响应头信息
                httpost.addHeader("Connection", "keep-alive");
                httpost.addHeader("Accept", "*/*");
                httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpost.addHeader("Host", "api.mch.weixin.qq.com");
                httpost.addHeader("X-Requested-With", "XMLHttpRequest");
                httpost.addHeader("Cache-Control", "max-age=0");
                httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
                httpost.setEntity(new StringEntity(xml, "UTF-8"));
                CloseableHttpResponse response = httpClient.execute(httpost);
                try {
                    HttpEntity entity = response.getEntity();

                    String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                    EntityUtils.consume(entity);
                    return jsonStr;
                } finally {
                    response.close();
                }
            } finally {
                httpClient.close();
            }

        } catch (Exception e) {
            // throw new BusinessException(BusinessCodeEnum.DATA_IS_NULL.getCode(), "微信退款加载证书失败，请联系管理员！");
        }
        return null;
    }

}
