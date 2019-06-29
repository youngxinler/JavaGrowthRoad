package com.functionalPrograming;

/**
 * @author youngxinler  19-6-29 下午8:07
 * 要求lambda表达式能够进行使用的两个条件:
 * 1.要求方法的入参是一个接口类型
 * 2.该接口类型必须是一个"functionalInterface", 即是函数式接口.
 * <p>
 * lambda表达式没有类型信息, 但是lamdba表达式的类型会由编译器进行推断, 如果存在多个类型满足要求,
 * 编译器推断不出来,那么就需要改写代码,帮助编译器进行判断, 方式有两种:
 * 1.显式地把lamda表达式赋值给一个确定类型的变量.
 * 2.直接显式地指定类型.
 * A a = () -> System.out.println("方式1");
 * (A)() -> System.out.println("方式2");
 * <p>
 * >lambda表达式中可以引用当前上下文的变量, 但是该变量引用必须是Immutable,
 * 或者变量本身就是Immutable(不可变).
 * >如果一个接口被设计为函数式接口，应该添加@FunctionalInterface,
 * 注解编译器会确保该接口确实是函数式接口,当尝试往该接口中添加新的方法时，编译器会报错。
 **/

public class LambdaTargetType {

    @FunctionalInterface
    interface A {
        void a();
    }

    @FunctionalInterface
    interface B {
        void b();
    }

    class UseAB {
        void use(A a) {
            System.out.println("use A");
        }

        void use(B b) {
            System.out.println("use B");
        }
    }

    void targetType() {
        UseAB useAB = new UseAB();
        //显式类型赋值, 帮助lambda指定类型
        A a = () -> System.out.println("use");
        useAB.use(a);
        //类型转化, 帮助lambda指定类型
//        useAB.use((A)() -> System.out.println("use"));
    }

    public static void main(String[] args) {
        LambdaTargetType lm = new LambdaTargetType();
        lm.targetType();
    }
}
