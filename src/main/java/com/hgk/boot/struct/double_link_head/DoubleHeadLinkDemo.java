package com.hgk.boot.struct.double_link_head;

import com.hgk.boot.struct.single_link.SingleLinkDemo;
import lombok.Data;

/**
 * 双链表(带头节点)
 */
public class DoubleHeadLinkDemo {

    @Data
    public static class User{
        public Integer id;
        public String name;
        public String address;

        public User(Integer id, String name, String address){
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("【");
            sb.append(id);
            sb.append(",");
            sb.append(name);
            sb.append(",");
            sb.append(address);
            sb.append("】");
            return  sb.toString();
//            return "【" + id + "," + name + "," + address + "】";
        }

        public boolean isEqualKey(User user){
            return this.id.equals(user.getId());
        }

        public boolean equals(User user){
            if(this == user)
                return true;
            return this.id.equals(user.id) && this.name.equals(user.name)
                    && this.address.equals(user.address);
        }

        public int hashCode(){
            return id.hashCode() + name.hashCode() + address.hashCode();
        }

    }

    public static void main(String[] args){
        DoubleHeadLink<User> link = new DoubleHeadLink<>();

        //---插---
        link.headInsert(new Node<>(new User(3,"李晓溪","山西")));
        link.printLink();
        link.afterInsert(new Node<>(new User(1,"郝国坤","山西")));
        link.printLink();
        link.insertByIndex(0,new Node<>(new User(42,"李白","安徽")));
        link.insertByIndex(2,new Node<>(new User(55,"杨倩","浙江")));
        link.insertByIndex(4,new Node<>(new User(72,"马云","浙江")));
        link.insertByIndex(5,new Node<>(new User(26,"秦奋","陕西")));
        link.insertByIndex(6,new Node<>(new User(33,"振宇","北京")));
        link.printLink();
        //---改---
        link.updateById(new Node<>(new User(1,"郝国坤","山西河津")));
        link.printLink();
        link.updateByIndex(1,new Node<>(new User(42,"李白","安徽的")));
        link.printLink();
        //---查---
        System.out.println(link.get(3).getData().toString());
        System.out.println(link.getAfter(2).getData().toString());
        System.out.println(link.getAfterTwo(2).getData().toString());
        System.out.println(link.get(new Node<>(new User(26,"秦奋","陕西"))));
        System.out.println(link.getLast().getData().toString());
        //---删---
        link.afterInsert(new Node<>(new User(34,"红旗","南京")));
        link.afterInsert(new Node<>(new User(88,"小飞","上海")));
        link.afterInsert(new Node<>(new User(89,"欧阳","上海")));
        link.printLink();
        link.headDelete();
        link.printLink();
        link.afterDelete();
        link.printLink();
        link.deleteFirstByAddress("山西");
        link.printLink();
        link.deleteAllByAddress("浙江");
        link.printLink();
        link.delete(new Node<>(new User(1,"郝国坤","山西河津")));
        link.printLink();
        link.deleteByIndex(3);
        link.printLink();
        //--反转---
        link.reverse();
        link.printLink();

    }
}
