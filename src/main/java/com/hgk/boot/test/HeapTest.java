package com.hgk.boot.test;

public class HeapTest {
    public static void main(String[] args){
        long initialMenory = Runtime.getRuntime().totalMemory()/1024/1024;
        long maxMenory = Runtime.getRuntime().maxMemory()/1024/1024;

        System.out.println("initialMenory:" + initialMenory + "M");
        System.out.println("maxMenory:" + maxMenory + "M");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
