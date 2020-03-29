package com.study.shiwu.entity;    /**
 * @author: wxs
 * @date: 2020/3/12
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author zm
 * @date 2020/3/12 14:57
 */
@Data
@ApiModel("用户")
public class Use {
    private Integer id;
    private String account;
    private String pwd;
    private String uname;

    public Use(Integer id, String account, String pwd, String uname) {
        this.id = id;
        this.account = account;
        this.pwd = pwd;
        this.uname = uname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
