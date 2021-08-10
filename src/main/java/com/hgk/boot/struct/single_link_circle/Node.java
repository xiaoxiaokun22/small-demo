package com.hgk.boot.struct.single_link_circle;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Getter
@Setter
public class Node<E> {
    E data;
    Node<E> next;

    public Node(E data){
        this.data = data;
        this.next = null;

    }

    public Node(E data, Node<E> pre, Node<E> next){
        this.data = data;
        this.next = next;
    }

    public boolean equalsData(Node<E> node){
        E data = this.getData();
        Boolean res = false;
        try {
            Method method = data.getClass().getDeclaredMethod("equals",node.getData().getClass());
            res = (Boolean) method.invoke(data,node.getData());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;

    }
}
