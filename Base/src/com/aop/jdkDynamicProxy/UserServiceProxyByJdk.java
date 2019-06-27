package com.aop.jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author youngxinler  19-6-26 下午7:49
 **/

public class UserServiceProxyByJdk implements InvocationHandler {
    private Object target;

    public UserServiceProxyByJdk(Object target) {
        this.target = target;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("---begin " + method.getName());
        Object result = method.invoke(target, objects);
        System.out.println("---end " + method.getName());
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , target.getClass().getInterfaces(), this);
    }
}
