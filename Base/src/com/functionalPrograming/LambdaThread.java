package com.functionalPrograming;

/**
 * @author youngxinler  19-6-29 下午8:04
 * <p>
 * 要求lambda表达式能够进行使用的两个条件:
 * 1.要求方法的入参是一个接口类型
 * 2.该接口类型必须是一个"functionalInterface", 即是函数式接口.
 **/
public class LambdaThread {
    public static void main(String[] args) {
        //lambda
        new Thread(() -> System.out.println("this is lambda thread start style")).start();

        //传统
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is traditional style");
            }
        }).start();
    }
}
