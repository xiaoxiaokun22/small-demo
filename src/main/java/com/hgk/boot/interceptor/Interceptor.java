package com.hgk.boot.interceptor;


import java.lang.reflect.InvocationTargetException;

public interface Interceptor {

    boolean before();

    void after();

    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    void afterReturing();

    void afterThrowing();

    boolean useAround();
}
