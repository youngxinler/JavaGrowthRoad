package com.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author youngxinler  2019/10/27
 **/
public class Main {
    public static void main(String[] args) throws Exception{
//        getClassInfoByReflect();
//        invokeMethodByReflect();
        tryChangeFinalFiled();
//        tryChangeFinalFieldFromConstruct();
    }

    private static void tryChangeFinalFieldFromConstruct() throws Exception{
        Father father = new Father();
        Class clazzOfFather = Father.class;
        Field descField = clazzOfFather.getDeclaredField("DESC");
        System.out.println("修改之前的DESC : " + father.getDesc());
        descField.setAccessible(true);
        descField.set(father, "不了, 我不爱吃橘子.");
        System.out.println("修改之后的DESC : " + father.getDesc());
    }

    private static void tryChangeFinalFiled()throws Exception{
        Father father = new Father();
        Class clazzOfFather =  Father.class;
        Field descField = clazzOfFather.getDeclaredField("DESC");
        System.out.println("修改之前的DESC : " + father.getDesc());
        if (descField != null){
            descField.setAccessible(true);
            descField.set(father, "不了, 不爱吃橘子");
        }
        System.out.println("修改之后的DESC : " + father.getDesc());
    }



    private static void invokeMethodByReflect()throws Exception{
        Father father = new Father();
        Class clazzOfFather = Father.class;
        Method setIdMethod = clazzOfFather.getDeclaredMethod("setId", long.class);

        //通过直接修改字段也可以

//        Field idField = clazzOfFather.getDeclaredField("id");
//        idField.setAccessible(true);
//        idField.set(father, 50);

        System.out.println("之前的father id : " + father.getId());
        if (setIdMethod != null){
            setIdMethod.setAccessible(true);
            setIdMethod.invoke(father, 50);
        }
        System.out.println("修改过的father id : " + father.getId());
    }

    private static void getClassInfoByReflect()throws Exception{
        Class father = Father.class;
        Field[] fields = father.getFields();
        System.out.println("这是Father类能公共访问的字段: : ");
        for (Field field : fields){
            System.out.println(field.toString());
        }
        Field[] declaredFields = father.getDeclaredFields();
        System.out.println("这是Father类单独声明的所有字段: ");
        for (Field field : declaredFields){
            System.out.println(field.toString());
        }

        Method[] methods = father.getMethods();
        Method[] declaredMethods = father.getDeclaredMethods();
        Method setAgeMethod = father.getMethod("setAge", int.class);

        System.out.println("这是Father类能公共访问的函数(包括从Object父类继承来的):");
        for (Method m : methods){
            System.out.println(m);
        }
        System.out.println("这是Father类单独声明的所有函数:");
        for (Method m : declaredMethods){
            System.out.println(m);
        }
    }
}

