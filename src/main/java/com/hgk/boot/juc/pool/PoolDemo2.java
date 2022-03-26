package com.hgk.boot.juc.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 有四个线程:thread-01,thread-02,thread-03，thread-03,主线程，怎么让 thread-03，thread-04线程 等待其他3个线程执行完再执行？
 * 解决方案1:
 * CyclicBarrier,可以让一组线程到达屏障后再全部继续执行，还可以在到达屏障后，继续执行前，加一个线程一些处理
 * 两个构造函数：
 * CyclicBarrier(5)
 * CyclicBarrier(5,()->{
 *     //屏障后，继续执行前,做一些处理
 * })
 * 注意：
 * 1.不可以让主线程阻塞
 * 2.可以重新初始化，意思就是CyclicBarrier参数的值在到达屏障后，又初始化为原来的值。
 */
public class PoolDemo2 {
    public static void main(String[] args) throws InterruptedException {

    }
}
