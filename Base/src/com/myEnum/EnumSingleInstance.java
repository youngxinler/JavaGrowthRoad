package com.myEnum;

/**
 * @author youngxinler  19-6-20 下午5:40
 * @version 0.1
 **/

public enum EnumSingleInstance {
    SINGLEINSTANCE;

    //单例中要存储的变量
    private String var1 = "i am a var of single instance.";
    private Integer var2 = 666;

    //单例中的方法
    public void method1() {
        System.out.println("i am method1, var1 = " + var1);
    }

    public void method2() {
        System.out.println("i am method2, var2 =" + var2);
    }

    public void displayStr(String str) {
        System.out.println(str);
    }

    private void method3() {
        System.out.println("i am method3, but every can't see me");
    }

    EnumSingleInstance() {
    }
}
