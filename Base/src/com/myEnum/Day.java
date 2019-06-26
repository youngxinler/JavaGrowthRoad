package com.myEnum;

public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    //枚举可以拥有自己的方法
    public void display() {
        System.out.println(this.toString());
    }
}
