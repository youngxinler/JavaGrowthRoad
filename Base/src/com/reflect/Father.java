package com.reflect;

/**
 * @author youngxinler  2019/10/27
 **/
public class Father {
    public String name;
    private int age;
    private long id;

//    private final String DESC = null == null ? "你在这站着等我下, 我去买几个橘子." : null;

    private final String DESC;

    public Father() {
        this.DESC = "你在这站着等我下, 我去买几个橘子.";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return DESC;
    }
}
