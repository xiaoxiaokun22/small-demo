package com.hgk.boot.juc.single_mode;

/**
 * 单例模式在多线程产生什么问题
 * 测试场景1.单例模式在单线程下获取实例，没问题
 * 测试场景2.单例模式在多线程下获取实例，出现创建多个实例的问题。
 * 测试场景2的解决方案：1.synchronized修饰getInstance()  2.synchronized代码块 和 双端检测单例机制
 */
public class SingleMode {

    private static SingleMode instance = null;
    public SingleMode(){
            System.out.println("Thread " + Thread.currentThread().getName() + " 构造SingleMode对象");
    }

//    public static SingleMode getInstance(){
//        if(instance == null){
//            instance = new SingleMode();
//        }
//        return instance;
//    }

    //测试场景2的解决方案：1.synchronized修饰getInstance()
//    public synchronized static SingleMode getInstance(){
//        if(instance == null){
//            instance = new SingleMode();
//        }
//        return instance;
//    }

    //测试场景2的解决方案：2.synchronized代码块 和 双端检测单例机制
    public static SingleMode getInstance(){
        if(instance == null){
            synchronized (SingleMode.class){
                if(instance == null){
                    instance = new SingleMode();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args){

        for(int i=0;i<10;i++){
            new Thread(()->{
                SingleMode.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
