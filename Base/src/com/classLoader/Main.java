package com.classLoader;

import com.classLoader.test.MyString;


import java.lang.reflect.Method;



public class Main {
    public static void main(String[] args) throws Exception{
        MyClassLoader classLoader = new MyClassLoader("E:\\JavaGrowthRoad\\Base\\target\\classes");
        Class stringClass = classLoader.loadClass("com.classLoader.test.MyString");

        MyString str = (MyString)stringClass.newInstance();
        Method setStr = stringClass.getDeclaredMethod("setStr", String.class);
        setStr.setAccessible(true);
        setStr.invoke(str, "hello");
        System.out.println(str.getStr());
        System.out.println(str.toString());
    }
}
