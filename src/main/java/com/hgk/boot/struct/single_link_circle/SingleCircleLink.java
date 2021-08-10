package com.hgk.boot.struct.single_link_circle;

/**
 * 单向循环链表（不带头节点）
 * ---增---
 * 1.void insert(E element)
 * ---删---
 * 1.void delete(E element)
 * ---查---
 * 1.打印链表 void printLink();
 */
public class SingleCircleLink<E> {

    Node<E> head;

    int size;

    public SingleCircleLink(){
        head = null;
    }

    int getLength(){
        return size;
    }

    /**
     * 围坐一圈人，从第k个人开始，数m下，第m个人出列；接着从下一个人开始数m下，循环，直到所有人出列。
     * 产生一个出列的顺序
     * @param k 从第k个人开始
     * @param m 数m下
     * @return
     */
    void yuesefu(int k, int m){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        if(k > size){
            System.out.println("超出链表长度");
            return;
        }
        Node<E> cur;
        Node<E> pre = null;
        int i = 1;
        int j = 1;
        boolean flag;
        for(cur = head;head != null;cur = cur.next){
            flag = false;
            if(i >= k){
                if(j == m){
                    System.out.println("出队：" + cur.getData().toString());
                    if(cur.next == cur)
                        head = null;
                    else
                        pre.next = cur.next;
                    size--;
                    j = 0;
                    flag = true;
                }
                j++;
            }
            i++;
            if(!flag)
                pre = cur;
        }
    }

    void insert(E element){
        Node<E> node = new Node<>(element);
        if(head == null){
            head = node;
            node.next = head;
            size++;
            return;
        }
        Node<E> cur;
        for(cur = head;cur.next != head;cur =cur.next){
        }
        node.next = head;
        cur.next = node;
        size++;
        return;
    }

    /**
     * 打印链表
     */
    void printLink(){
        if(head == null){
            System.out.println("打印链表为空");
            return;
        }
        Node<E> cur = head;
        StringBuffer sb = new StringBuffer();
        while (true){
            E data = cur.data;
            sb.append(data.toString());
            if(cur.next == head)
                break;
            sb.append("=>");
            cur = cur.next;
        }
        System.out.print( "size:" + size + ","+ sb.toString());
        System.out.println();
    }

}
