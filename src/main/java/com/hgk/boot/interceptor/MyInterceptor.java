package com.hgk.boot.interceptor;

import java.lang.reflect.InvocationTargetException;

public class MyInterceptor implements Interceptor {

    @Override
    public boolean before() {
        System.out.println("before...");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after...");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before...");
        Object obj = invocation.proceed();
        System.out.println("around after...");
        return obj;
    }

    @Override
    public void afterReturing() {
        System.out.println("afterReturing...");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }

    @Override
    public boolean useAround() {
        System.out.println("useAround...");
        return true;
    }
}
