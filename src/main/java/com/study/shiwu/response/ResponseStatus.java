package com.study.shiwu.response;

public enum ResponseStatus {
    SUCCESS("0001","操作成功"),
    ERRORS("0002","失败"),
    MONEY_BU_ZU("0003","余额不足"),
    TONG_MING("0004","此用户已存在"),
    TOKEN_NOT_EXIST("00000021","缺少token"),
    TOKEN_EXPIRE("00000022","token过期"),
    NOTE_EXPIRE("00000023","验证码错误");












    private String code;
    private String desc;
    public String getCode() {
        return code;
    }
    public String getDesc() { return desc; }
    ResponseStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
