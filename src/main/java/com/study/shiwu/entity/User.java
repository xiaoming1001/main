package com.study.shiwu.entity;

import javax.validation.constraints.NotNull;

/**
 * @author ASUS
 */
public class User {
    @NotNull(message = "用户ID不能为空")
    private int id;
    private String uname;
    private String card;
    private double money;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "is=" + id +
                ", uanme='" + uname + '\'' +
                ", card='" + card + '\'' +
                ", money=" + money +
                '}';
    }
}
