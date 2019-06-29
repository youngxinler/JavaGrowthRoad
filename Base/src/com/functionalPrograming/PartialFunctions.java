package com.functionalPrograming;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author youngxinler  19-6-29 下午4:37
 **/

//部分函数   -重用了biFunction -设置了默认值10
public class PartialFunctions {
    private static <T, U, R> Function<U, R> partialLeft(BiFunction<T, U, R> biFunction, T t) {
        return (u) -> biFunction.apply(t, u);
    }

    private static <T, U, R> Function<T, R> partialRight(BiFunction<T, U, R> biFunction, U u) {
        return (t) -> biFunction.apply(t, u);
    }

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> biFunction = (v1, v2) -> v1 - v2;
        Function<Integer, Integer> subtractFrom10 = partialLeft(biFunction, 10);
        Function<Integer, Integer> subtractBy10 = partialRight(biFunction, 10);
        System.out.println(subtractFrom10.apply(5));
        System.out.println(subtractBy10.apply(5));
    }
}
