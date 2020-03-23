package com.study.shiwu.error;
import com.study.shiwu.response.ResponseStatus;
import lombok.Data;

/**
 * 定义异常
 * */

public class ZengError extends RuntimeException {
    private ResponseStatus responseStatus;

    //提供类的构造，注入枚举类
    public ZengError(ResponseStatus responseStatus){
        this.responseStatus=responseStatus;
    }



    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
