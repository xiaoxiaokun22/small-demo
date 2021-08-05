package com.hgk.boot.proxy;

import com.hgk.boot.interceptor.Interceptor;
import com.hgk.boot.interceptor.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBean
        implements InvocationHandler
{

    private Object target = null;
    private Interceptor interceptor = null;

    /**
     * 绑定被代理对象target
     * @param target    被代理对象
     * @param interceptor 拦截器
     * @return
     */
    public static Object getProxyBean(Object target, Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = target;
        proxyBean.interceptor = interceptor;
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),proxyBean);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target,method,args);
        try {
            if(this.interceptor.before()){
                this.interceptor.around(invocation);
            }else{
                method.invoke(target,args);
            }
        }catch (Exception e){
            exceptionFlag = true;
        }
        this.interceptor.after();
        if(exceptionFlag){
            this.interceptor.afterThrowing();
        }else{
            this.interceptor.afterReturing();
        }
        return null;
    }

}
