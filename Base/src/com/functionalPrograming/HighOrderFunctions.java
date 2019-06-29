package com.functionalPrograming;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author youngxinler  19-6-29 下午4:27
 **/


//https://www.ibm.com/developerworks/cn/java/j-understanding-functional-programming-2/index.html
//高阶函数
public class HighOrderFunctions {
    private static <T> Predicate<T> notEqual(T t) {
        return (v) -> !Objects.equals(v, t);
    }

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3)
                .stream()
                .filter(notEqual(1))
                .forEach(System.out::println);
    }
}
