package com.hgk.boot.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程定制化通信(线程按指定顺序运行)
 * A线程执行5次，B线程执行10次，C线程执行15次
 * 重复上述操作6次
 */
class ShareSource{

    private int flag = 1;//1（a线程） 2（b线程） 3（c线程）

    private final ReentrantLock reenLock = new ReentrantLock();

    private Condition aCond = reenLock.newCondition();
    private Condition bCond = reenLock.newCondition();
    private Condition cCond = reenLock.newCondition();

    public void print5(int loop) throws InterruptedException {
        reenLock.lock();
        try {
            while(flag != 1){
                aCond.await();
            }
            for(int i=0;i<5;i++){
                System.out.printf("Thread %s 第%d次打印，轮次：%d\n",Thread.currentThread().getName()
                        ,i,loop);
            }
            flag = 2;
            bCond.signal();
//            aCond.signalAll();
        }finally {
            reenLock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        reenLock.lock();
        try {
            while(flag != 2){
                bCond.await();
//                aCond.await();
            }
            for(int i=0;i<10;i++){
                System.out.printf("Thread %s 第%d次打印，轮次：%d\n",Thread.currentThread().getName()
                        ,i,loop);
            }
            flag = 3;
            cCond.signal();
//            aCond.signalAll();
        }finally {
            reenLock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        reenLock.lock();
        try {
            while(flag != 3){
                cCond.await();
//                aCond.await();
            }
            for(int i=0;i<15;i++){
                System.out.printf("Thread %s 第%d次打印，轮次：%d\n",Thread.currentThread().getName()
                        ,i,loop);
            }
            flag = 1;
            aCond.signal();
//            aCond.signalAll();
        }finally {
            reenLock.unlock();
        }
    }

}
public class ThreadDemo02 {
    public static void main(String[] args){
        ShareSource shareSource = new ShareSource();
        int loop = 6;
        new Thread(()->{
            for(int i=0;i<loop;i++){
                try {
                    shareSource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for(int i=0;i<loop;i++){
                try {
                    shareSource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for(int i=0;i<loop;i++){
                try {
                    shareSource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
