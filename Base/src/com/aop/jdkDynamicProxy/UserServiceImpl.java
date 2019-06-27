package com.aop.jdkDynamicProxy;

/**
 * @author youngxinler  19-6-26 下午7:47
 **/

public class UserServiceImpl implements UserService {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
