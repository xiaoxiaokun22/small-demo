package com.hgk.boot.zdemo14.cglib_dynamic_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before,do something");
        Object obj = methodProxy.invokeSuper(o,objects);
        System.out.println("after,do something");
        return obj;
    }

    public Object getProxy(Class<?> targetClass){
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(targetClass.getClassLoader());
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MyMethodInterceptor());
        return enhancer.create();
    }
}
