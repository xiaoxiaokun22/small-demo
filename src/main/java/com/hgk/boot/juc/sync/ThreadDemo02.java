package com.hgk.boot.juc.sync;


/**
 * 可重入锁
 */
public class ThreadDemo02 {

    public static void main(String[] args){
        Object o = new Object();
        new Thread(()->{
            synchronized(o){
                System.out.println(Thread.currentThread().getName() + " 外层");
                synchronized(o){
                    System.out.println(Thread.currentThread().getName() + " 中层");
                }
                synchronized(o){
                    System.out.println(Thread.currentThread().getName() + " 内层");
                }
            }
        },"t1").start();
    }
}
