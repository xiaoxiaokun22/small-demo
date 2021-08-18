package com.hgk.boot.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable接口创建线程
 * 使用FutureTask类
 */
public class ThreadDemo06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new Thread(new MyThread01(),"t1").start();
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        new Thread(futureTask,"t2").start();
        while (!futureTask.isDone()){
            System.out.println("wait...");
        }
        System.out.println(futureTask.get());
        System.out.println(futureTask.get());

    }
}

class MyThread01 implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + "::Runnable接口");
    }
}

class MyThread2 implements Callable{
    @Override
    public Object call() throws Exception {
        Thread.sleep(2);
        System.out.println("Thread " + Thread.currentThread().getName() + "::Callable接口");
        return 1024;
    }
}
