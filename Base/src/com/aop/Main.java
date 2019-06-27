package com.aop;


import com.aop.jdkDynamicProxy.UserService;
import com.aop.jdkDynamicProxy.UserServiceImpl;
import com.aop.jdkDynamicProxy.UserServiceProxyByJdk;

/**
 * @author youngxinler  19-6-26 下午7:26
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        UserServiceProxyByJdk userServiceProxyByJdk = new UserServiceProxyByJdk(service);
        UserService userServiceProxy = (UserService) userServiceProxyByJdk.getProxy();
        userServiceProxy.sayHello();
    }
}
