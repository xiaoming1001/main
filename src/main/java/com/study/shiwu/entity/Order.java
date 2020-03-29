package com.study.shiwu.entity;    /**
 * @author: wxs
 * @date: 2020/3/23
 */

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author zm
 * @date 2020/3/23 10:10
 */
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String account;
    private String uName;
    private String phone;
    private String site;
    private String placeTime;

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

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", uName='" + uName + '\'' +
                ", phone='" + phone + '\'' +
                ", site='" + site + '\'' +
                ", placeTime='" + placeTime + '\'' +
                '}';
    }
}
