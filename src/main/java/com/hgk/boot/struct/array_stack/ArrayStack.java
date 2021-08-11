package com.hgk.boot.struct.array_stack;

import java.util.Stack;

public class ArrayStack<E> {

    Object[] elementData;

    int maxSize;

    int top;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.elementData = new Object[maxSize];
        this.top = -1;
    }

    public int getSize(){
        return elementData.length;
    }

    public void push(E element){
        if(isFull()){
            throw new RuntimeException("栈已经满了");
        }
        top++;
        elementData[top] = element;
    }

    /**
     * 取出栈顶值，不删除
     * @return
     */
    public E peek(){
        if(isEmpty()){
            System.out.println("栈空了");
            return null;
        }
        return (E) elementData[top];
    }

    /**
     * 取出栈顶值，并删除
     * @return
     */
    public E pop(){
        if(isEmpty()){
            System.out.println("栈空了");
            return null;
        }
        E res = (E) elementData[top];
        top--;
        return res;
    }

    public boolean isFull(){
        return maxSize == (top + 1);
    }

    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 打印栈，从栈顶开始
     */
    public void printStack(){
        for(int i = top;i != -1;i--){
            System.out.print(elementData[i]);
            if(i != 0)
                System.out.print("=");
        }
    }
}
