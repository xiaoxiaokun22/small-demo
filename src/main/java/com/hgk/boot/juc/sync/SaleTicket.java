package com.hgk.boot.juc.sync;


/**
 * 模拟三个销售员卖票
 */
class Ticket{
    private int num = 30;

    public synchronized void sale(){
        if(num > 0){
            System.out.printf("%s卖票，当前有%d张票，卖出还剩%d\n",Thread.currentThread().getName(),num--,num);
        }
    }
}

public class SaleTicket {
    public  static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        //AA线程
        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"AA").start();
        //BB
        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"BB").start();
        //CC
        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"CC").start();
    }


}
