package com.example.tannguyen.moneyapplication;

public class HistoryAct {
    String address;
    String money;
    String time;

    public HistoryAct(String address, String money, String time) {
        this.address = address;
        this.money = money;
        this.time = time;
    }
    public HistoryAct() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
