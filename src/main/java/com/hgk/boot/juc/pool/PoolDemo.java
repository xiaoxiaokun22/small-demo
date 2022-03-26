package com.hgk.boot.juc.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

/**
 * 有四个线程:thread-01,thread-02,thread-03，thread-03,主线程，怎么让 thread-03，thread-04线程 等待其他3个线程执行完再执行？
 * 解决方案1:
 * CountDownLatch,可以让一个或者多个线程倒计时结束后进入阻塞状态。
 * 注意：
 * 1.也可以让主线程阻塞（CyclicBarrier不可以）
 * 2.不能重新初始化
 */
public class PoolDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-子线程执行");
            latch.countDown();
        },"thread-01").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "-子线程执行");
            latch.countDown();
        },"thread-02").start();

        new Thread(()->{
            System.out.println("333333");
            try {
                //阻塞当前线程直到latch的值为0
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-子线程执行");
        },"thread-03").start();

        new Thread(()->{
            try {
                //阻塞当前线程直到latch的值为0
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-子线程执行");
            latch.countDown();
        },"thread-04").start();
//        latch.await();
        System.out.println("主线程执行");
        latch.countDown();
    }
}
