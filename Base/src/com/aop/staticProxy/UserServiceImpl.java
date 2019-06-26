package com.aop.staticProxy;

/**
 * @author youngxinler  19-6-26 下午7:28
 * @version 0.1
 **/

public class UserServiceImpl implements UserService {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
