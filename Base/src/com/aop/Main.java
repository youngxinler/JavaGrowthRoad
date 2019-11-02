package com.aop;

import com.aop.cglibProxy.CglibProxy;
import com.aop.cglibProxy.UserService;
import com.aop.cglibProxy.UserServiceImpl;


/**
 * @author youngxinler  19-6-26 下午7:26
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        cglibProxy();
    }



    private static void cglibProxy(){
        CglibProxy cglibTools = new CglibProxy();
        UserService userServiceProxy = (UserService) cglibTools.getProxy(UserServiceImpl.class);
        userServiceProxy.sayHello();
    }
}
