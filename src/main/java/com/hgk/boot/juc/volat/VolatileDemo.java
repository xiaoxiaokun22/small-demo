package com.hgk.boot.juc.volat;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

class UserData{
    //test01()---验证volatile可见性---
//    volatile int age = 0;
////    int age = 0;
//    public void setAge(int newAge){
//        age = newAge;
//    }

    //test02()---验证volatile不保证原子性---
//    volatile int age = 0;
//    public void ageIncr(){
//        age++;
//    }

    //test03()---解决volatile不保证原子性造成的问题（Atomic）---
    AtomicInteger atomicAge = new AtomicInteger(0);
    public void ageAtomicIncr(){
        atomicAge.getAndIncrement();
    }

    //test04()---解决volatile不保证原子性造成的问题(synchronized)---
    volatile int age = 0;
    public synchronized void ageIncr(){
        age++;
    }
}
public class VolatileDemo {
    public static void main(String[] args){
//        test01();
//        test02();
//        test03();
        test04();
    }

    /**
     * 1.验证volatile具有可见性
     * 思路步骤：
     * 新建一个线程t1,让它修改一个UserData实例的age字段，
     * 然后在不使用和使用volatile的情况下，看其他线程（这里就看main线程）是否知道age字段修改了。
     */
    public static void test01(){
//        UserData userData = new UserData();
//        new Thread(()->{
//            System.out.println("Thread " + Thread.currentThread().getName() + " 开始执行");
//            //可能在修改age之前，花费了1秒执行了其他操作
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            userData.setAge(20);
//            System.out.println("Thread " + Thread.currentThread().getName() + " 修改了age，读取age值为：" + userData.age);
//        },"t1").start();
//        while (userData.age != 20){
//            //main线程不知道age被修改为20的话，就一直循环
//        }
//        System.out.println("Thread " + Thread.currentThread().getName() + " 执行，读取age值为：" + userData.age);
    }

    /**
     * 2.验证volatile不保证原子性
     * 思路步骤：
     * 创建10个线程，让每个线程执行ageIncr()方法1000次，执行计算完后，让主线程打印age结果。
     * 如果保证了原子性，结果age应为10000
     */
    public static void test02(){
//        UserData userData = new UserData();
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                for(int j=0;j<1000;j++){
//                    userData.ageIncr();
//                }
//            },String.valueOf(i)).start();
//        }
//        //为啥是2，默认主线程和GC线程在运行，如果超过2，说明还有线程没计算完。
//        while (Thread.activeCount() > 2){
//            //此时要将main线程的运行状态变为就绪状态，让其他线程先运行。
//             Thread.yield();
//        }
//        System.out.println("age值为：" + userData.age);
    }

    /**
     * 3.解决volatile不保证原子性造成的问题(数据丢失)
     * 解决方案1:
     * 使用Atomic***类
     * 例如AtomicInteger类，内部定义了成员变量volatile int value;
     * 就相当于在volatile的特性基础上，Atomic类自己再添加了保证原子性的操作。
     */
    public static void test03(){
//        UserData userData = new UserData();
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                for(int j=0;j<1000;j++){
//                    userData.ageAtomicIncr();
//                }
//            },String.valueOf(i)).start();
//        }
//        while (Thread.activeCount() > 2){
//            Thread.yield();
//        }
//        System.out.println("age值为：" + userData.atomicAge);
    }

    /**
     * 3.解决volatile不保证原子性造成的问题(数据丢失)
     * 解决方案2:
     * 使用synchronized可以解决，但是太重了，杀鸡用牛刀
     */
    public static void test04(){
        UserData userData = new UserData();
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<1000;j++){
                    userData.ageIncr();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("age值为：" + userData.age);
    }
}
