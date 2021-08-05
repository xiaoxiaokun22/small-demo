package com.hgk.boot.zdemo14;

/**
 * 被代理类
 */
public class PersonImpl implements Person {

    @Override
    public void saylove(String msg){
        System.out.println(msg);
    }
}
