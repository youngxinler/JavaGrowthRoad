package com.aop;

import com.aop.staticProxy.UserService;
import com.aop.staticProxy.UserServiceImpl;
import com.aop.staticProxy.UserServiceProxy;

/**
 * @author youngxinler  19-6-26 下午7:26
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
        userServiceProxy.sayHello();
    }
}
