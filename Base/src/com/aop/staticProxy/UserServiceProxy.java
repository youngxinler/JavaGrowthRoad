package com.aop.staticProxy;

/**
 * @author youngxinler  19-6-26 下午7:29
 * @version 0.1
 **/

public class UserServiceProxy {
    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    public void sayHello() {
        System.out.println("how are you?");
        userService.sayHello();
        System.out.println("goodbye~");
    }
}
