package com.hgk.boot.juc.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 五.集合的线程安全
 */
public class ThreadDemo03 {
    public static void main(String[] args){
//        test();
//        test01();
        test02();
    }

    /**
     * ArrayList线程不安全问题-并发修改异常
     * 解决方案：
     * 1.Vector
     * 2.Collections.synchronizedList()
     * 3.CopyOnWriteArrayList
     *   可以并发读，但写的时候要先复制一个原数组，写完后再覆盖
     */
    public static void test(){
        //重现Arraylist问题，ConcurrentModificationException并发修改异常
//        List<String> list = new ArrayList<>();
        //1.Vector
//        List<String> list = new Vector<>();
        //2.Collections.
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        //3.CopyOnWriteArrayList
        List<String> list =  new CopyOnWriteArrayList();
        for(int i=0;i<20;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * HashSet线程不安全问题-并发修改异常
     * 解决方案
     * 1.CopyOnWriteArraySet
     */
    public static void test01(){
        //ConcurrentModificationException
//        Set<String> list =  new HashSet<>();
        //1.CopyOnWriteArraySet
        Set<String> list =  new CopyOnWriteArraySet<>();
        for(int i=0;i<20;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * HashMap线程不安全问题-并发修改异常
     * 解决方案
     * 1.ConcurrentHashMap
     */
    public static void test02() {
        //ConcurrentModificationException
//        Map<String,Object> map =  new HashMap<>();
        //1.CopyOnWriteArraySet
        Map<String,Object> map = new ConcurrentHashMap<>();
        for(int i=0;i<20;i++){
            int finalI = i;
            new Thread(()->{
                map.put(String.valueOf(finalI),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
