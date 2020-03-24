package com.study.shiwu.util;    /**
 * @author: wxs
 * @date: 2020/3/23
 */

import com.alibaba.fastjson.JSON;
import com.study.shiwu.constant.NoteVerifyStatic;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zm
 * @date 2020/3/23 17:18
 * 短信验证工具类
 */
public class NoteUtil {

   public static String add(String phone) throws Exception {
       CloseableHttpClient httpClient = HttpClientBuilder.create().build();

       HttpPost httpPost = new HttpPost(NoteVerifyStatic.SERVER_URL);
       String curTime = String.valueOf((new Date()).getTime() / 1000L);

       //参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
       String checkSum = CheckSumBuilder.getCheckSum(NoteVerifyStatic.APP_SECRET, NoteVerifyStatic.NONCE, curTime);

       // 设置请求的header
       httpPost.addHeader("AppKey", NoteVerifyStatic.APP_KEY);
       httpPost.addHeader("Nonce", NoteVerifyStatic.NONCE);
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
       nvps.add(new BasicNameValuePair("templateid", NoteVerifyStatic.TEMPLATEID));
       nvps.add(new BasicNameValuePair("mobile", phone));
       nvps.add(new BasicNameValuePair("codeLen", NoteVerifyStatic.CODELEN));

       httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

       // 执行请求
       HttpResponse response = httpClient.execute(httpPost);
       //获得执行结果
       String result = EntityUtils.toString(response.getEntity(), "utf-8");

       //--------------------- 接受返回参数，判断---------------------
       String obj = JSON.parseObject(result).getString("obj");
       //获取发送状态码
       String code = JSON.parseObject(result).getString("code");
        //返回结果
       return result;
   }

}
