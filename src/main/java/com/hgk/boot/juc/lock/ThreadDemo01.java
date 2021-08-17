package com.hgk.boot.juc.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信
 * 实现四个线程，随机交替执行加一减一操作10次，打印出来为1，0，1，0...
 * 线程执行顺序不确定
 */
class Share{
    private int num = 0;

    private final ReentrantLock reenLock = new ReentrantLock();

    private Condition condition = reenLock.newCondition();

    public void incr() throws InterruptedException {
        reenLock.lock();
        try {
            while(num != 0 ){
                condition.await();
            }
            num++;
            System.out.printf("Thread %s 执行了加1操作，当前数为%d\n",Thread.currentThread().getName(),num);
            condition.signalAll();
        }finally {
            reenLock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        reenLock.lock();
        try {
            while(num != 1 ){
                condition.await();
            }
            num--;
            System.out.printf("Thread %s 执行了减1操作，当前数为%d\n",Thread.currentThread().getName(),num);
            condition.signalAll();
        }finally {
            reenLock.unlock();
        }
    }
}
public class ThreadDemo01 {
    public static void main(String[] args){
        Share share = new Share();
        //A
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        //B
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        //C
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        //D
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
