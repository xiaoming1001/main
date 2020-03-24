package com.study.shiwu.Test;    /**
 * @author: wxs
 * @date: 2020/3/23
 */

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.study.shiwu.util.CheckSumBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * @author zm
 * @date 2020/3/23 16:00
 */
public class MsgSend {
    //---------------------- 固定格式----------------
    //发送验证码的请求路径URL
    private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APP_KEY = "165549ede8e1101724b2e64ed486438f";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET = "16c19517fbc9";
    //随机数
    private static final String NONCE = "123456";
    //短信模板ID
    private static final String TEMPLATEID = "14853087";
    //需要给哪个手机号发送
    private static final String MOBILE = "18781111908";
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN = "6";

    public static void main(String[] args) throws Exception {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);

        //参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
        nvps.add(new BasicNameValuePair("mobile", MOBILE));
        nvps.add(new BasicNameValuePair("codeLen", CODELEN));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        // 打印执行结果{"code":200,"msg":"105","obj":"410626"}
        System.out.println(result);


        //--------------------- 接受返回参数，判断---------------------
        String obj = JSON.parseObject(result).getString("obj");
        System.out.println("验证码是：" + obj);
        //获取发送状态码
        String code = JSON.parseObject(result).getString("code");

        String phoneStr=obj;
        if (code.equals("200") && obj.equals(phoneStr)) {
            // 发送成功
            System.out.println("登录成功");
        } else {
            // 发送失败
            System.out.println("验证码不正确");
        }

    }


}
