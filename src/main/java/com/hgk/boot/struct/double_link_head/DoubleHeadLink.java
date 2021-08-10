package com.hgk.boot.struct.double_link_head;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  双链表(带头节点)
 *  ---初始化---
 *  1.初始化链表 构造函数DoubleHeadLink()
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
 *  7.从尾到头打印链表 void printLinkAfter() TODO
 *  ---反转---
 *  1.反转链表 void reverse()
 */
public class DoubleHeadLink<E> {

    Node<E> head;

    int size;

    public DoubleHeadLink(){
        this.head = new Node<>(null);
    }

    void clear(){
        this.head = null;
        size = 0;
    }

    int getLength(){
        return size;
    }
    /**
     * 尾插法
     */
    void afterInsert(Node<E> node){
        Node<E> cur;
        for(cur = head.next;cur != null;cur = cur.next){
            if(cur.next == null){
                cur.next = node;
                node.pre = cur;
                size++;
                return;
            }
        }
        head.next = node;
        node.pre = head;
        /**
         * 这里有个坑，java互相引用，Node节点不要使用@Data注解。
         * 如果使用，@Data会生成toString()方法，会造成栈溢出异常。
         */
        size++;
    }

    /**
     * 头插法
     */
    void headInsert(Node<E> node){
        size++;
        if(head.next == null){
            head.next = node;
            node.pre = head;
            return;
        }
        node.next = head.next;
        head.next.pre = node;
        node.pre = head;
        head.next = node;
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
        for(Node<E> cur = head; cur != null; cur = cur.next){
            E data = cur.data;
            sb.append(data == null?"【null】": data.toString());
            if(cur.next != null )
                sb.append("=>");
        }
        System.out.print( "size:" + size + ","+ sb.toString());
        System.out.println();
    }

    /**
     * 指定第n个节点之后插入一个节点
     */
    void insertByIndex(int n,Node<E> node){
        if(n > size && n < 0){
            System.out.println("超出链表长度");
        }
        if(n == 0){
            node.next = head.next;
            if(head.next != null)
                head.next.pre = node;
            head.next = node;
            node.pre = head;
            size++;
            return;
        }
        Node<E> cur;
        int i = 1;
        for(cur = head.next;cur != null;cur = cur.next){
            if(i == n){
                node.next = cur.next;
                if(cur.next != null)
                    cur.next.pre = node;
                cur.next = node;
                node.pre = cur;
                size++;
                return;
            }
            i++;
        }
    }

    /**
     * 修改指定节点
     */
    void updateById(Node<E> node){
        Node<E> cur;
        boolean isEqualKey = false;
        for(cur = head.next;cur != null;cur = cur.next){
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
        if(n > size || n <= 0){
            System.out.println("超出链表长度");
            return;
        }
        Node<E> cur;
        int i = 1;
        for(cur = head.next;cur != null;cur = cur.next){
            if(i == n){
                node.next = cur.next;
                if(cur.next != null)
                    cur.next.pre = node;
                cur.pre.next = node;
                node.pre = cur.pre;
                return;
            }
            i++;
        }
    }

    /**
     * 查询第n个节点
     */
    Node<E> get(int n){
        if(n > size || n <= 0){
            throw new RuntimeException("超出链表长度");
        }
        Node<E> cur;
        int i = 1;
        for(cur = head.next;cur != null;cur = cur.next){
            if(i == n){
                return cur;
            }
            i++;
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
     * 快慢指针法
     */
    Node<E> getAfterTwo(int n){
        Node<E> fast;
        Node<E> slow = head;
        int i = 1;
        for(fast = head.next;fast != null;fast = fast.next){
            if(i >= n){
                slow = slow.next;
            }
            i++;
        }
        return slow;
    }

    /**
     * 获取指定节点的位置
     */
    int get(Node<E> node){
        int i = 1;
        Node<E> cur;
        for (cur = head.next; cur != null; cur = cur.next){
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
        if(head.next  == null){
            throw new RuntimeException("getLast()链表为空");
        }
        int i = 1;
        Node<E> cur;
        for (cur = head.next; cur.next != null; cur = cur.next){

        }
        return cur;
    }

    /**
     * 头部删除
     */
    void headDelete(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        Node<E> cur = head.next;
        head.next = cur.next;
        if(cur.next != null)
            cur.next.pre = head;
        size--;
    }

    /**
     * 尾部删除
     */
    void afterDelete(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        Node<E> cur;
        for(cur = head;cur.next != null;cur = cur.next){
        }
        cur.pre.next = null;
        size--;
    }

    /**
     * 删除出现的第一个元素
     */
    void deleteFirstByAddress(String address){
       Node<E> cur;
        for(cur = head.next; cur != null; cur = cur.next){
            String addrValue = null;
            try {
                Field addrField = cur.getData().getClass().getField("address");
                addrValue = (String) addrField.get(cur.getData());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if(addrValue.equals(address)){
                cur.pre.next = cur.next;
                if(cur.next != null)
                    cur.next.pre = cur.pre;
                size--;
                break;
            }
        }
    }

    /**
     * 删除出现的所有元素
     */
    void deleteAllByAddress(String address){
        Node<E> cur;
        for(cur = head.next; cur != null; cur = cur.next){
            String addrValue = null;
            try {
                Field addrField = cur.getData().getClass().getField("address");
                addrValue = (String) addrField.get(cur.getData());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if(addrValue.equals(address)){
                cur.pre.next = cur.next;
                if(cur.next != null)
                    cur.next.pre = cur.pre;
                size--;
            }
        }
    }

    /**
     * 删除指定节点
     */
    void delete(Node<E> node){
        Node<E> cur;
        for (cur = head.next; cur != null; cur = cur.next){
            if(node.equalsData(cur)){
                cur.pre.next = cur.next;
                if(cur.next != null)
                    cur.next.pre = cur.pre;
                size--;
                break;
            }
        }
    }

    /**
     * 删除第n个节点
     */
    void deleteByIndex(int n){
        if(n > size || n < 1){
            System.out.println("超出链表长度");
            return;
        }
        int i = 1;
        Node<E> cur;
        for(cur = head.next;cur != null;cur = cur.next){
            if(i == n){
                cur.pre.next = cur.next;
                if(cur.next != null)
                    cur.next.pre = cur.pre;
                size--;
                break;
            }
            i++;
        }
    }

    /**
     * 反转链表
     * 两个指针，一个新表头
     */
    void reverse(){
        Node<E> newHead = new Node<>(null);
        Node<E> cur;
        Node<E> next;
        for(cur = head.next;cur != null;cur = next){
            next = cur.next;
            cur.next = newHead.next;
            if(newHead.next != null)
                newHead.next.pre = cur;
            newHead.next = cur;
            cur.pre = newHead;
        }
        head = newHead;
    }

}
