package com.example.wechat;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class BaiduTest {

    // 官网获取的 API Key 更新为你注册的
    String clientId = "(此处需自己填写)";
    // 官网获取的 Secret Key 更新为你注册的
    String clientSecret = "(此处需自己填写)";

    public static final String APP_ID="22310170";
    public static final String APP_KEY="IRz0iM43Ab9bVSXwoNU4mBiv";
    public static final String SECRET_KEY="Yd8bNNtUDmf8CIaZTgKCW2hM8Sw7oeTc";

    public static void main(String[] args) {
        AipOcr aipocr=new AipOcr(APP_ID,APP_KEY,SECRET_KEY);
        sample(aipocr);
    }

    @Test
    public static void sample(AipOcr client){
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String image = "c:/Users/hang_/Desktop/111.png";
        JSONObject res = client.basicGeneral(image, options);
        try {
            System.out.println(res.toString(2));
        }catch (Exception e){
            e.printStackTrace();
        }

        // 参数为本地图片二进制数组
//        byte[] file = GetImage(image);
//        res = client.basicGeneral(file, options);
//        System.out.println(res.toString(2));


        // 通用文字识别, 图片参数为远程url图片
       // JSONObject res = client.basicGeneralUrl(url, options);
       // System.out.println(res.toString(2));

    }

    /**
     * url图片转成byt[]
     * @param imgs
     * @return
     */
    public static byte[] GetImage(String imgs) {
        try {
            URL url = new URL(imgs);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte data[] = readInputStream(inStream);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
