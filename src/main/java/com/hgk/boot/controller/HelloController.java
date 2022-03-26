package com.hgk.boot.controller;

import com.hgk.boot.service.HelloService;
import com.hgk.boot.service.ValidateService;
import com.hgk.boot.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;
    @Autowired
    private TestServiceImpl testService;

    public long count = 0;

    public static Integer number;
    public static boolean ready;

    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name){
        testService.testOther();
        return helloService.sayHello(name);
    }

    @RequestMapping("/helloValidate")
    public String sayHelloValidate(){
        String name = "12345";
        ValidateService validateService = (ValidateService) helloService;
        if(validateService.validateName(name)){
            return helloService.sayHello(name);
        }
        return "";
    }

    @RequestMapping("/count")
    public synchronized long countTest(){
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ++count;
    }
    public static class ReaderThread extends Thread{
        @Override
        public void run(){
            while (!ready)
                Thread.yield();
            System.out.println("number:" + number);
        }

    }

    @RequestMapping("/main")
    public static void main(){
        new ReaderThread().start();
        number = 232323;
        ready = true;
    }

}
