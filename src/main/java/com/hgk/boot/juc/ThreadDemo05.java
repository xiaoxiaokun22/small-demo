package com.hgk.boot.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 模拟死锁产生
 */
public class ThreadDemo05 {

    public static void main(String[] args){
        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(()->{
            synchronized (lockA){
                System.out.println(Thread.currentThread().getName() + "已经获得锁A,接下来尝试获取锁B");
                try {
                    //暂停一会，让t2线程先运行，触发死锁现象
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName() + "获取了锁B");
                }
            }
        },"t1").start();
        new Thread(()->{
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "已经获得锁B,接下来尝试获取锁A");
                synchronized (lockA){
                    System.out.println(Thread.currentThread().getName() + "获取了锁A");
                }
            }
        },"t2").start();

    }
}
