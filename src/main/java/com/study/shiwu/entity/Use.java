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
@ApiModel("测试token")
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
}
