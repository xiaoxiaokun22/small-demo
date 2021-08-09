package com.hgk.boot.struct.queue;

/**
 * 循环队列（数组实现）
 * 初始化指针：frond = 0, rear = 0
 * 判空条件：rear == front
 * 判满条件：(rear + 1) % maxSize = front
 */
public class CircleQueue {

    private static final String EMPTY_QUEUE_DESC = "队列已经空了！";

    private static final String FULL_QUEUE_DESC = "队列已经满了";

    //存放队列
    int arr[];
    //队列头 第一个元素
    int front;
    //队列尾 下一个要插入的元素位置
    int rear;
    //队列容量
    int maxSize;

    public CircleQueue(int maxSize){
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
        rear = (rear + 1) % maxSize;
    }

    /**
     * 出队
     */
    public int getQueue(){
        if(isEmpty()){
            System.out.println(EMPTY_QUEUE_DESC);
        }
        int data = arr[front];
        front = (front + 1) % maxSize;
        return data;
    }

    /**
     * 展示队列的数据
     */
    public void showQueue(){
       int validCount = getValidCount();
       for(int i = front;i<front + validCount;i++){
           System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
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
        return (rear + 1) % maxSize == front;
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

    /**
     * 有效数据
     */
    public int getValidCount(){
        /**
         * maxSize 6
         * rear 1
         * front 5
         * validCount = 2
         */
        return (rear-front+maxSize) % maxSize;
    }

}
