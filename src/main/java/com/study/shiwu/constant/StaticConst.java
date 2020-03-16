package com.study.shiwu.constant;

import io.swagger.annotations.ApiModel;

@ApiModel("常量池")
public class StaticConst {
    //token秘钥
    public static final String  APP_TOKEN_SECRET = "xiao_ming_xian_sheng";
    //token过期时间
    public static final int  APP_TOKEN_TIME_OUT = 1000*60*30;

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TX_STREAM_PUSH_KEY = "ed1b0cc7c6f849914acce849466fb40e";
    public static final String TX_STREAM_PLAY_URL = "qtzhibo.cdjsw.cn";
    public static final String TX_STREAM_PUSH_URL = "qtzb.cdjsw.cn";

    public static final String RTMP_PROTOCOL = "rtmp://";
    public static final String HTTP_PROTOCOL = "http://";

    public static final String APP_NAME = "qt";
    public static final String WX_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String WX_APP_ID = "wxa3afd3d739675965";
    public static final String WX_SECRET = "4a15c084bf0be152d7be7c1d81f9271d";
    public static final String WX_SESSION_GRANT_TYPE = "authorization_code";
    public static final String WX_TOKEN_GRANT_TYPE = "client_credential";
    public static final String WX_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final Integer CODE_SUCCESS = 200;
    public static final String SESSION_KEY_CODE_ERRCODE = "errcode";
    public static final String AES = "AES";
    public static final String AES_CBC_PADDING = "AES/CBC/PKCS7Padding";
    public static final String MCH_ID = "1575799881";
    public static final String MCH_KEY = "123456789012345678901234567890ab";
    public static final long IM_EXPIRE = 180*86400;
    public static final long IM_SKDAPP = 1400320711;
    public static final String IM_SECRET = "60cdba98bc44385890c4b6a3fce8936109ed03eb30e57df0b9bd7a02a59b893a";
    public static final String IM_ADMIN = "qingteng";
    public static final String SIGNTYPE = "MD5";
    public static final String NOTIFY_URL = "qtzbapi.dev.cdjsw.cn/wxPay/callback";
    public static final String TRADETYPE = "JSAPI";
    public static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
