package com.hgk.boot.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestServiceImpl {

    @PostConstruct
    public void test(){
        System.out.println("===PostConstruct finish===");
    }

    public void testOther(){
        System.out.println("test other");
    }
}
