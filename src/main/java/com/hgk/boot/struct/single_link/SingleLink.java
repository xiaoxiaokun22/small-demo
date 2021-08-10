package com.hgk.boot.struct.single_link;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 *  单链表(不带头节点)
 *  ---初始化---
 *  1.初始化链表 构造函数SingleLink()
 *  2.清空链表 void clear()
 *  ---增---
 *  1.尾插法 void afterInsert(Node<E> node)
 *  2.头插法 void headInsert(Node<E> node)
 *  3.指定第n个节点之后插入一个节点 void insertByIndex(int n,Node<E> node)
 *  ---删---
 *  1.头部删除 void headDelete()
 *  2.尾部删除 void afterDelete()
 *  3.删除出现的第一个元素 void deleteFirstByAddress(String address)
 *  4.删除出现的所有元素 void deleteAllByAddress(String address)
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
 *  7.从尾到头打印链表 void printLinkAfter()
 *  ---反转---
 *  1.反转链表 void reverse()
 *  ---其他---
 *  1.合并两个有序的单链表，合并之后的链表依然有序 TODO
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
        Node<E> cur = head;
        while (true){
            if(cur.next == null){
                cur.next = node;
                size++;
                break;
            }
            cur = cur.next;
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

    Node<E> headInsert(Node<E> head,Node<E> node){
        if(head == null){
            head = node;
            size++;
            return head;
        }
        node.next = head;
        head = node;
        size++;
        return head;
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
        for(Node<E> cur = head;cur != null;cur = cur.next,i++){
            if(i == n){
                node.next = cur.next;
                cur.next = node;
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
//        Node<E> cur = head;
//        do{
//            E data = cur.data;
//            sb.append(data.toString());
//            if(cur.next != null )
//                sb.append("=>");
//            cur = cur.next;
//        }while (cur != null);
        //
        for(Node<E> cur = head;cur != null;cur = cur.next){
            E data = cur.data;
            sb.append(data.toString());
            if(cur.next != null )
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
        for(Node<E> cur = head;cur != null;cur = cur.next){
            E data = cur.getData();
            try {
                Method method = data.getClass().getDeclaredMethod("isEqualKey",data.getClass());
                isEqualKey = (Boolean) method.invoke(data,node.getData());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                break;
            }
            if(isEqualKey){
                cur.setData(node.getData());
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
        for(Node<E> cur = head;cur != null;cur = cur.next,i++){
            if(i == n){
                node.next = cur.next;
                if(pre == null)
                    head = node;
                else
                    pre.next = node;
                break;
            }
            pre = cur;
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
        for(Node<E> cur = head;cur != null;cur = cur.next,i++){
            if(i == n)
                return cur;
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
        for (Node<E> cur = head;cur != null;cur = cur.next){
            if(node.equalsData(cur)){
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
        Node<E> cur = head;
        do{
            cur = cur.next;
        }while (cur.next != null);
        return cur;
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
        Node<E> cur = head;
        Node<E> pre = null;
        while (cur.next != null){
            pre = cur;
            cur = cur.next;
        }
        if(pre == null)
            head = head.next;
        else
            pre.next = cur.next;
        size--;
    }

    /**
     * 删除指定节点
     * 两个指针：当前指针，前一个指针
     */
    void delete(Node<E> node){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        Node<E> pre = null;
        for (Node<E> cur = head;cur != null;cur = cur.next){
            if(node.equalsData(cur)){
                if(pre == null)
                    head = head.next;
                else
                    pre.next = cur.next;
                size--;
                break;
            }
            pre = cur;
        }
    }

    /**
     * 删除第n个节点
     */
    void deleteByIndex(int n){
        if(n > size || n <= 0){
            System.out.println("超出链表长度");
            return;
        }
        Node<E> pre = null;
        int i = 1;
        for(Node<E> cur=head;cur != null;cur = cur.next){
            if(i == n){
                if(pre == null)
                    head = head.next;
                else
                    pre.next = cur.next;
                size--;
                break;
            }
            i++;
            pre = cur;
        }
    }

    /**
     * 删除出现的第一个元素
     */
    void deleteFirstByAddress(String address){
        Node<E> pre = null;
        for(Node<E> cur=head;cur != null;cur = cur.next){
            String addrValue = null;
            try {
                Field addrField = cur.getData().getClass().getField("address");
                addrValue = (String) addrField.get(cur.getData());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if(addrValue.equals(address)){
                if(pre == null)
                    head = head.next;
                else
                    pre.next = cur.next;
                size--;
                break;
            }
            pre = cur;
        }
    }

    /**
     * 删除出现的所有元素
     */
    void deleteAllByAddress(String address){
        Node<E> pre = null;
        for(Node<E> cur=head;cur != null;cur = cur.next){
            String addrValue = null;
            try {
                Field addrField = cur.getData().getClass().getField("address");
                addrValue = (String) addrField.get(cur.getData());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if(addrValue.equals(address)){
                if(pre == null)
                    head = head.next;
                else
                    pre.next = cur.next;
                size--;
            }else{
                pre = cur;
            }
        }
    }

    /**
     * 反转链表
     * 两个指针：cur指针，next指针；一个新表头
     */
    void reverse(){
        Node<E> newHead = null;
        Node<E> cur;
        Node<E> next;//临时保存当前节点的下个节点
        for(cur = head;cur != null; cur = next){
            size++;
            next = cur.next;
            cur.next = newHead;
            newHead = cur;
        }
        head = newHead;
    }

    /**
     * 从尾到头打印链表
     * 使用栈数据结构的先进后出
     */
    void printLinkAfter(){
        Stack<Node<E>> stack = new Stack<>();
        Node<E> cur;
        for(cur = head;cur != null;cur=cur.next){
            stack.push(cur);
        }
        StringBuffer sb = new StringBuffer();
        for(cur = head;cur != null;cur=cur.next){
            Node<E> node = stack.pop();
            E data = node.data;
            sb.append(data.toString());
            if(cur.next != null )
                sb.append("=>");
        }
        System.out.print( "逆序size:" + size + ","+ sb.toString());
    }
}
