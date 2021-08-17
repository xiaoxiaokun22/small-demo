package com.hgk.boot.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟三个销售员卖票
 */
class Ticket{
    private int num = 30;

    //创建可重入锁,默认为非公平锁
    private final ReentrantLock reenlock = new ReentrantLock();

    public void sale(){
        reenlock.lock();
        try {
            if(num > 0){
                System.out.printf("%s卖票，当前有%d张票，卖出还剩%d\n",Thread.currentThread().getName(),num--,num);
            }
        }finally {
            reenlock.unlock();
        }
    }
}

public class SaleTicket {
    public static void main(String[] args){
        Ticket ticket = new Ticket();
        //AA线程
        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"AAA").start();
        //BB
        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"BBB").start();
        //CC
        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"CCC").start();
    }
}
