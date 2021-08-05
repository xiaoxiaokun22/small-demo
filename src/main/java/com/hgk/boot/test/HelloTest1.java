package com.hgk.boot.test;

public class HelloTest1 {
    public static void main(String[] args){
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
