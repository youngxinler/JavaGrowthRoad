package com;

import com.yEnum.EnumSingleInstance;

/**
 * @author youngxinler  19-6-20 下午3:55
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        EnumSingleInstance instance = EnumSingleInstance.SINGLEINSTANCE;
        instance.method1();
        instance.method2();
        instance.displayStr("this is a single instance's display method!");
    }
}
