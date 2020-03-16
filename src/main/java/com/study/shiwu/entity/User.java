package com.study.shiwu.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ASUS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户二号")
public class User {
    @NotNull(message = "用户ID不能为空")
    private int id;
    private String uname;
    private String card;
    private double money;

}
