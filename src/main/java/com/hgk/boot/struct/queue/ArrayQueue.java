package com.hgk.boot.struct.queue;

import lombok.Data;

/**
 * 普通队列（数组实现）
 * 初始化指针 front = 0 , rear = 0
 * 判空条件：front == rear
 * 判满条件：rear == maxSize
 */
@Data
public class ArrayQueue {

    private static final String EMPTY_QUEUE_DESC = "队列已经空了！";

    private static final String FULL_QUEUE_DESC = "队列已经满了";

    //存放队列
    int arr[];
    //队列头
    int front;
    //队列尾
    int rear;
    //队列容量
    int maxSize;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.front = 0;
        this.rear = 0;
        this.arr = new int[maxSize];
    }

    /**
     * 入队
     */
    public void addQueue(int data){
        if(isFull()){
            System.out.println(FULL_QUEUE_DESC);
            return;
        }
        arr[rear] = data;
        rear++;
    }

    /**
     * 出队
     */
    public int getQueue(){
        if(isEmpty()){
            System.out.println(EMPTY_QUEUE_DESC);
        }
        int data = arr[front];
        arr[front++] = 0;
        return data;
    }

    /**
     * 展示队列的数据
     */
    public void showQueue(){
        for(int i = 0;i<arr.length;i++){
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    /**
     * 是否为空
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 是否已满
     */
    public boolean isFull(){
        return rear == maxSize;
    }

    /**
     * 展示队列的第一个数据，不取出
     */
    public int headQueue(){
        if(isEmpty()){
            System.out.println(EMPTY_QUEUE_DESC);
        }
        return arr[front];
    }


}
