package com;

import com.myEnum.EnumSingleInstance;

import java.io.IOException;

/**
 * @author youngxinler  19-6-20 下午3:55
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
//        EnumSingleInstance instance = EnumSingleInstance.SINGLEINSTANCE;
//        instance.method1();
//        instance.method2();
//        instance.displayStr("this is a single instance's display method!");

        Fruit apple = new Apple("apple", 1);
        try {
            apple.displayName();
        }catch (IOException e){
            e.printStackTrace();
        }
//        apple.displayName();;
    }
}

class Fruit{
    protected String name;
    protected int number;

    public Fruit(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void displayName()throws IOException {
        System.out.println(name);
    }
}

class Apple extends Fruit{

    public Apple(String name, int number) {
        super(name, number);
    }

    @Override
    public void displayName(){
        try {
            super.displayName();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("son class");
    }

    public void disPlayNo(){
        System.out.println(number);
    }

}