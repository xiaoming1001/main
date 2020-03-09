package com.study.shiwu.entity;

/**
 * @author ASUS
 */
public class User {
    private int is;
    private String uname;
    private String card;
    private double money;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getIs() {
        return is;
    }

    public void setIs(int is) {
        this.is = is;
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
                "is=" + is +
                ", uanme='" + uname + '\'' +
                ", card='" + card + '\'' +
                ", money=" + money +
                '}';
    }
}
