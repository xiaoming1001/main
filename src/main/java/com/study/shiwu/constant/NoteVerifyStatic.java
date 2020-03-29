package com.study.shiwu.constant;    /**
 * @author: wxs
 * @date: 2020/3/23
 */

import io.swagger.annotations.ApiModel;

/**
 * @author zm
 * @date 2020/3/23 17:09
 */
@ApiModel("短信验证常量池")
public class NoteVerifyStatic {
    //发送验证码的请求路径URL
    public static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    public static final String APP_KEY = "b34fa24593a2d524a88c57a2b005d028";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    public static final String APP_SECRET = "93d44ec445ac";
    //随机数
    public static final String NONCE = "123456";
    //短信模板ID
    public static final String TEMPLATEID = "14836979";
    //验证码长度，范围4～10，默认为4
    public static final String CODELEN = "6";

}
