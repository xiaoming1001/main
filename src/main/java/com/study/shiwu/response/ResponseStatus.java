package com.study.shiwu.response;

public enum ResponseStatus {
    SUCCESS("0001","操作成功"),
    ERRORS("0002","失败"),
    MONEY_BU_ZU("0003","余额不足"),
    TONG_MING("0004","此用户已存在");
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
