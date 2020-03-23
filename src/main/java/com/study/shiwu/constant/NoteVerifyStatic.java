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
    private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APP_KEY = "165549ede8e1101724b2e64ed486438f";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET = "16c19517fbc9";
    //随机数
    private static final String NONCE = "510722";
    //短信模板ID
    private static final String TEMPLATEID = "14853087";
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN = "6";

}
