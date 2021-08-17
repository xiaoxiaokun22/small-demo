package com.hgk.boot.juc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ThreadDemo03 {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
