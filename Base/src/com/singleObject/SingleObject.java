package com.singleObject;

/**
 * @author youngxinler
 * @description TODO
 * @date 2020-08-25  22:59
 */
public class SingleObject {
    private static volatile SingleObject o;

    public static SingleObject getInstance(){
        if (o == null){
            synchronized(SingleObject.class){
                if (o == null){
                    o = new SingleObject();
                }
            }
        }
        return o;
    }
}
