package com.study.shiwu.entity;    /**
 * @author: wxs
 * @date: 2020/3/23
 */

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zm
 * @date 2020/3/23 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String account;
    private String uName;
    private String phone;
    private String site;
    private String placeTime;

}
