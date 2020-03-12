package com.study.shiwu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.study.shiwu.constant.StaticConst;
import com.study.shiwu.error.ZengError;
import com.study.shiwu.response.ResponseStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class JwtTokenUtil {

    public static String createToken() {
        //加密算法+自己的密钥对数据做一个签名
        Algorithm algorithm = Algorithm.HMAC256(StaticConst.APP_TOKEN_SECRET);
        //jwt.create()创建一个JWTCreator实例来创建一个令牌
        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject("用户")
                .withExpiresAt(new Date(System.currentTimeMillis() + StaticConst.APP_TOKEN_TIME_OUT))
                .sign(algorithm);
        return token;
    }

    //建立用户token(*********)
    public static String createUserToken(String  account) {
        Algorithm algorithm = Algorithm.HMAC256(StaticConst.APP_TOKEN_SECRET);
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("account", account)  //存放我们想放在token中的key--value
                .withSubject("用户")
                .withExpiresAt(new Date(System.currentTimeMillis() + StaticConst.APP_TOKEN_TIME_OUT))  //有效期
                .sign(algorithm);
        //签名+数据一起作为token返回，密钥别人不知道，无法伪造token
        System.out.println("工具类建立的token的是："+token);
        return token;
    }

    //建立管理员token
    public static String createAdminToken(Integer adminId) {
        Algorithm algorithm = Algorithm.HMAC256(StaticConst.APP_TOKEN_SECRET);
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("adminId", adminId)
                .withSubject("用户")
                .withExpiresAt(new Date(System.currentTimeMillis() + StaticConst.APP_TOKEN_TIME_OUT))
                .sign(algorithm);
        return token;
    }

    //建立教师token
    public static String createTeacherToken(Integer teacherId) {
        Algorithm algorithm = Algorithm.HMAC256(StaticConst.APP_TOKEN_SECRET);
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userId", teacherId)
                .withSubject("用户")
                .withExpiresAt(new Date(System.currentTimeMillis() + StaticConst.APP_TOKEN_TIME_OUT))
                .sign(algorithm);
        return token;
    }

    //验证token
    public static DecodedJWT verifyToken(String token) {
        System.out.println("验证token获取到的token："+token);
        if(token == null){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            throw new ZengError(ResponseStatus.TOKEN_NOT_EXIST);  //缺少token
        }
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        Algorithm algorithm = Algorithm.HMAC256(StaticConst.APP_TOKEN_SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        DecodedJWT decodedJwt;
        try {
            //此方法验证token是否过期
            decodedJwt = jwtVerifier.verify(token);
        }catch(TokenExpiredException e){
            throw new ZengError(ResponseStatus.TOKEN_EXPIRE);//token过期
        }
        //调用解码token方法
        JSONObject decode = decodeTokenPayload(token);
        System.out.println(decode.getInteger("exp") - Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0,10)));
        if (decode.getLong("exp") - Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0,10)) < 300) {
            String name = "";
            if(decode.containsKey("account")){
                name = "account";
                System.out.println("哈哈哈哈：");
            }else if(decode.containsKey("userId")){
                name = "userId";
            }else if(decode.containsKey("teacherId")){
                name = "teacherId";
            }
            String newtoken = JWT.create()
                    .withIssuer("auth0")
                    .withClaim(name, decode.getInteger(name))  //将新的token存入
                    .withSubject("用户")
                    .withExpiresAt(new Date(System.currentTimeMillis() + StaticConst.APP_TOKEN_TIME_OUT))
                    .sign(algorithm);

            response.setHeader("token", newtoken);
        }

        return decodedJwt;
    }

    //解码token
    public static JSONObject decodeTokenPayload(String token){
        String base64EncodedBody = JWT.decode(token).getPayload();
        Base64 base64Url = new Base64();
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject jsonObject = JSON.parseObject(body);
        System.out.println("解码出来的数据是："+jsonObject);
        return jsonObject;
    }

    //根据token获取用户ID
    public static Integer getUserIdByToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String[] splitString = token.split("\\.");
        String base64EncodedBody = splitString[1];
        Base64 base64Url = new Base64();
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject.getInteger("userId");
    }
}
