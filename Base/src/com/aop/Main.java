package com.aop;


//import com.aop.staticProxy.UserService;
//import com.aop.staticProxy.UserServiceImpl;
//import com.aop.staticProxy.UserServiceProxy;

//import com.aop.cglibProxy.CglibProxy;
//import com.aop.jdkDynamicProxy.UserService;
//import com.aop.jdkDynamicProxy.UserServiceImpl;
//import com.aop.jdkDynamicProxy.UserServiceProxyByJdk;

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

//    private static void bootStaticProxy(){
//        UserService service = new UserServiceImpl();
//        UserServiceProxy proxy = new UserServiceProxy(service);
//        proxy.sayHello();
//    }

//    private static void bootJdkProxy(){
//        UserService service = new UserServiceImpl();
//        UserServiceProxyByJdk userServiceProxyByJdk = new UserServiceProxyByJdk(service);
//        UserService userServiceProxy = (UserService) userServiceProxyByJdk.getProxy();
//        userServiceProxy.sayHello();
//    }

    private static void cglibProxy(){
        CglibProxy cglibTools = new CglibProxy();
        UserService userServiceProxy = (UserService) cglibTools.getProxy(UserServiceImpl.class);
        userServiceProxy.sayHello();
    }
}
