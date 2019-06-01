package com.graphic.guardedSuspension;

/**
 * @author youngxinler  19-5-19 下午8:37
 * @version 0.1
 **/

public class Request {
    private final String name;

    public Request(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "[ Request" + name + " ]";
    }
}
