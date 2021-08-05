package com.hgk.boot.service.impl;

import com.hgk.boot.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("hello "+ name);
        return name;
    }
}
