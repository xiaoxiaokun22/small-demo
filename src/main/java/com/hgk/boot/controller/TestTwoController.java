package com.hgk.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTwoController extends TestOneController{
    @RequestMapping("/dosomething")
    public synchronized void doSomething(){
        System.out.println("to do something");
        super.doSomething();
    }
}
