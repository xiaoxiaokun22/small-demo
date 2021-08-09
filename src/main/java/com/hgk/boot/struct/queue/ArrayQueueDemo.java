package com.hgk.boot.struct.queue;

public class ArrayQueueDemo {
    public static void main(String[] args){
        ArrayQueue arrayQueue = new ArrayQueue(6);
        arrayQueue.addQueue(32);
        arrayQueue.addQueue(42);
        arrayQueue.addQueue(231);
        arrayQueue.addQueue(4);
        arrayQueue.addQueue(319);

        System.out.printf("队列头指针:\n %d\n", arrayQueue.getFront());
        System.out.printf("队列尾指针:\n %d\n", arrayQueue.getRear());

        System.out.println("展示队列所有数据:");
        arrayQueue.showQueue();
        System.out.println("入队数据1018:");
        arrayQueue.addQueue(1018);
        arrayQueue.showQueue();
        System.out.println("是否满了:" + arrayQueue.isFull());
        System.out.println("是否空了:" + arrayQueue.isEmpty());
        System.out.println("出队5个元素:");
        arrayQueue.getQueue();
        arrayQueue.getQueue();
        arrayQueue.getQueue();
        arrayQueue.getQueue();
        arrayQueue.getQueue();
        arrayQueue.showQueue();


    }
}
