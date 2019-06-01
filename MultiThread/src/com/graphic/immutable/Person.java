package com.graphic.immutable;

/**
 * @author youngxinler  19-5-19 下午8:26
 * @version 0.1
 *
 * 通过final关键字， 确保了只能在字段初始化或类的构建方法中能够对final字段进行赋值操作
 * 而且java保证类的构造方法是一个原子操作， 所以在实例初始化后， 需要多线程进行操作的字段不可变的
 * 从而确保了多线程的安全性
 * 举例： String 类经过实例初始化后也是不可变的！
 **/

public class Person {
    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "[ Person: name = " + name + ", address = " + address + " ]";
    }
}
