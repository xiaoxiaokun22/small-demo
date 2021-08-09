package com.hgk.boot.struct.link;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 *  单链表(不带头节点)
 *  ---初始化---
 *  1.初始化链表 构造函数SingleLink()
 *  2.清空链表 void clear()
 *  ---增---
 *  1.尾插法 void headInsert(Node<E> node)
 *  2.头插法 void afterInsert(Node<E> node)
 *  3.指定第n个节点之后插入一个节点 void insertByIndex(int n,Node<E> node)
 *  ---删---
 *  1.头部删除 void headDelete()
 *  2.尾部删除 void afterDelete()
 *  3.删除出现的第一个元素 void deteleFirstByAddress(String address)
 *  4.删除出现的所有元素 void deteleAllByAddress(String address)
 *  5.删除指定节点 void delete(Node<E> node)
 *  6.删除第n个节点 void deleteByIndex(int n)
 *  ---改---
 *  1.修改指定节点 void updateById(Node<E> node)
 *  2.修改第n个节点 void updateByIndex(int n,Node<E> node)
 *  ---查---
 *  1.打印链表 void printLink()
 *  2.查询第n个节点 Node<E> get(int n)
 *  3.查询倒数第n个节点 Node<E> getAfter(int n)
 *  4.获取指定节点的位置 int get(Node<E> node)
 *  5.获取最后一个节点 Node<E> getLast()
 *  6.获取链表长度 int getLength()
 *  ---反转---
 *  1.反转链表 void reverse()
 */
public class SingleLink<E> {

    Node<E> head;

    int size;

    /**
     * 初始化链表
     */
    public SingleLink(){
        head = null;
    }

    /**
     * 清空链表
     */
    void clear(){
        head = null;
        size = 0;
    }

    /**
     * 获取链表长度
     */
    int getLength(){
        return size;
    }

    /**
     * 尾插法
     */
    void afterInsert(Node<E> node){
        if(head == null){
            head = node;
            size++;
            return;
        }
        Node<E> temp = head;
        while (true){
            if(temp.next == null){
                temp.next = node;
                size++;
                break;
            }
            temp = temp.next;
        }
        return;
    }

    /**
     * 头插法
     */
    void headInsert(Node<E> node){
        if(head == null){
            head = node;
            size++;
            return;
        }
        node.next = head;
        head = node;
        size++;
    }

    /**
     * 指定位置后插入一个节点
     */
    void insertByIndex(int n,Node<E> node){
        if(n > size || n < 0){
            System.out.println("超出链表长度");
            return;
        }
        if(n == 0){
            node.next = head;
            head = node;
            size++;
            return;
        }
        int i = 1;
        for(Node<E> temp = head;temp != null;temp = temp.next,i++){
            if(i == n){
                node.next = temp.next;
                temp.next = node;
                break;
            }
        }
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
        StringBuffer sb = new StringBuffer();
        //循环方式二
//        Node<E> temp = head;
//        do{
//            E data = temp.data;
//            sb.append(data.toString());
//            if(temp.next != null )
//                sb.append("=>");
//            temp = temp.next;
//        }while (temp != null);
        //
        for(Node<E> temp = head;temp != null;temp = temp.next){
            E data = temp.data;
            sb.append(data.toString());
            if(temp.next != null )
                sb.append("=>");
        }
        System.out.print( "size:" + size + ","+ sb.toString());
        System.out.println();
    }

    /**
     * 修改指定节点
     * 需要E自定义 isEqualKey(E) 方法
     */
    public void updateById(Node<E> node){
        if(node == null)
            return;
        Boolean isEqualKey = false;
        for(Node<E> temp = head;temp != null;temp = temp.next){
            E data = temp.getData();
            try {
                Method method = data.getClass().getDeclaredMethod("isEqualKey",data.getClass());
                isEqualKey = (Boolean) method.invoke(data,node.getData());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                break;
            }
            if(isEqualKey){
                temp.setData(node.getData());
                break;
            }
        }
    }
    /**
     * 修改第n个节点
     */
    void updateByIndex(int n,Node<E> node){
        if(n > size || n < 1){
            System.out.println("超出链表长度");
            return;
        }
        int i = 1;
        Node<E> pre = null;
        for(Node<E> temp = head;temp != null;temp = temp.next,i++){
            if(i == n){
                node.next = temp.next;
                if(pre == null)
                    head = node;
                else
                    pre.next = node;
                break;
            }
            pre = temp;
        }
    }

    /**
     * 查询第n个节点
     */
    Node<E> get(int n){
        if(n > size || n < 0){
            throw new RuntimeException("超出链表长度");
        }
        int i = 1;
        for(Node<E> temp = head;temp != null;temp = temp.next,i++){
            if(i == n)
                return temp;
        }
        return null;
    }

    /**
     * 查询倒数第n个节点
     * 1.顺序遍历：（两次循环）循环一遍求出size，变成查询正数第size-n个节点
     * 2.快慢指针法：（一次循环）两个指针，让一个比另一个快n个节点，知道快指针为空时，慢指针就是倒数第n个节点。
     */
    Node<E> getAfter(int n){
        if(n > size || n <= 0){
            throw new RuntimeException("超出链表长度");
        }
        return get(size-n+1);
    }

    /**
     * 查询倒数第n个节点
     * 快慢指针法
     */
    Node<E> getAfterTwo(int n){
        if(n > size || n <= 0){
            throw new RuntimeException("超出链表长度");
        }
        Node<E> slow = head;
        int j = 1;
        for(Node<E> fast = head;fast != null;fast = fast.next){
            if(j > n){
                slow = slow.next;
            }
            j++;
        }
        return slow;
    }

    /**
     * 获取指定节点的位置
     */
    int get(Node<E> node){
        int i = 0;
        for (Node<E> temp = head;temp != null;temp = temp.next){
            if(node.equalsData(temp)){
                break;
            }
            i++;
        }
        return i;
    }

    /**
     * 获取最后一个节点
     */
    Node<E> getLast(){
        Node<E> temp = head;
        do{
            temp = temp.next;
        }while (temp.next != null);
        return temp;
    }

    /**
     * 头部删除
     */
    void headDelete(){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        head = head.next;
        size--;
    }

    /**
     * 尾部删除
     */
    void afterDelete(){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        Node<E> temp = head;
        Node<E> pre = null;
        while (temp.next != null){
            pre = temp;
            temp = temp.next;
        }
        if(pre == null)
            head = head.next;
        else
            pre.next = temp.next;
        size--;
    }

    /**
     * 删除指定节点
     */
    void delete(Node<E> node){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        Node<E> pre = null;
        for (Node<E> temp = head;temp != null;temp = temp.next){
            if(node.equalsData(temp)){
                if(pre == null)
                    head = head.next;
                else
                    pre.next = temp.next;
                size--;
                break;
            }
            pre = temp;
        }
    }

    /**
     * 删除第n个节点
     */
    void deleteByIndex(int n){

    }

    /**
     * 删除出现的第一个元素
     */
    void deteleFirstByAddress(String address){

    }

    /**
     * 删除出现的所有元素
     */
    void deteleAllByAddress(String address){

    }

}
