package com.hgk.boot.config;

import com.hgk.boot.service.ValidateService;
import com.hgk.boot.service.impl.ValidateServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//@Order(2)
public class MyAspect {

    /**
     * 引入，HelloServiceImpl增强
     */
    @DeclareParents(value = "com.hgk.boot.service.impl.HelloServiceImpl+"
            ,defaultImpl = ValidateServiceImpl.class)
    public ValidateService validateService;

    @Pointcut("execution(* com.hgk.boot.controller.HelloController.*(..))")
    public void pointCut(){}
    @Before("pointCut()")
    public void doBefore(JoinPoint jp){
        for(Object o: jp.getArgs()){
            System.out.println("参数："+ o.toString());

        }
        System.out.println("before");
    }
    @After("pointCut()")
    public void doAfter(JoinPoint jp){}
    @Around("pointCut()")
    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before doAround");
        pjp.proceed();
        System.out.println("after doAround");
    }
    @AfterReturning(pointcut = "pointCut()")
    public void doAfterReturing(JoinPoint jp){
        System.out.println("AfterReturning");
    }
    @AfterThrowing(pointcut = "pointCut()")
    public void doAfterThrowing(JoinPoint jp){

        System.out.println("AfterThrowing");
    }
}
