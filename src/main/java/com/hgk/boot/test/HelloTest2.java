package com.hgk.boot.test;

import java.util.ArrayList;
import java.util.Random;

/**
 * 堆空间溢出过程
 */
public class HelloTest2 {

    byte[] bytes = new byte[new Random().nextInt(1024*100)];

    public static void main(String[] args){
        ArrayList<HelloTest2> list = new ArrayList<>();
        while (true){
            list.add(new HelloTest2());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
