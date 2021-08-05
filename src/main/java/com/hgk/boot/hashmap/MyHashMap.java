package com.hgk.boot.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyHashMap<K,V> {

    /**
     *
     * 数组下标的计算方法为（n为数组长度）：  (n - 1) & hash 等价于 hash % n；
     */

    //原表
    transient Node<K,V>[] table;

    //实际存储元素的大小
    transient int size;

    transient int modCount;

    //哈希表默认的大小16（数组默认初始化的大小）
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    //链表转红黑树的阈值
    static final int TREEIFY_THRESHOLD = 8;

    //红黑树转链表的阈值
    static final int UNTREEIFY_THRESHOLD = 6;

    static class Node<K,V> implements Map.Entry<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public boolean equals(Object o){
            if (o == this)
                return true;
            if(o instanceof Map.Entry){
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if(Objects.equals(key,e.getKey()) && Objects.equals(value,e.getValue())){
                    return true;
                }
            }
            return false;
        }
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    public V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict){
        Node<K,V>[] tab;
        Node<K,V> p;
        int n,i;
        if((tab = table) == null){

        }
        return null;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
