package com.study.shiwu.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("响应消息体")
public class ResponseBody<T> {
    @ApiModelProperty("响应码")
    private String code;
    @ApiModelProperty("响应消息")
    private String msg;
    @ApiModelProperty("响应状态")
    private String status;
    @ApiModelProperty("响应数据")
    private T data;

    public ResponseBody(){}
    //携带数据返回
    public ResponseBody(ResponseStatus responseStatus,T data){
        this.code=responseStatus.getCode();
        System.out.println("输出："+responseStatus.getCode());//此处响应码引用枚举类的响应码
        this.status=responseStatus.name();
        System.out.println("输出："+responseStatus.name());//此处状态引用枚举声明的 SUCCESS/ERROES
        this.msg=responseStatus.getDesc();
        System.out.println("输出："+responseStatus.getDesc());//此处消息引用枚举类的定义消息
        this.data=data;
        System.out.println("返回响应的数据："+data);
    }

    //只返回消息
    public ResponseBody(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.msg = responseStatus.getDesc();
        this.status = responseStatus.name();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
