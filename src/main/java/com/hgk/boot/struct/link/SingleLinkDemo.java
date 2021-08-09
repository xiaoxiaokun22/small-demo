package com.hgk.boot.struct.link;

import lombok.Data;

public class SingleLinkDemo {
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
        //---创建链表---
        SingleLink<User> singleLink = new SingleLink<>();
        //---增---
        //尾插法添加元素
        singleLink.printLink();
        User wpUser = new User(1,"王鹏","山西");
        singleLink.afterInsert(new Node<>(wpUser));
        singleLink.afterInsert(new Node<>(new User(2,"金涛","河南")));
        singleLink.afterInsert(new Node<>(new User(6,"陈述","福建")));
        singleLink.printLink();
        //头插法添加元素
        singleLink.headInsert(new Node<>(new User(3,"刘辉","江西")));
        singleLink.printLink();
        //第2个节点后面插入
        singleLink.insertByIndex(1,(new Node<>(new User(4,"黄轩","江西"))));
        singleLink.printLink();
        //---改---
        //修改id为1的用户信息
        wpUser.setAddress("山西-运城");
        singleLink.updateById(new Node<>(wpUser));
        singleLink.printLink();
        //修改第1个节点的用户
        singleLink.updateByIndex(1,new Node<>(new User(101,"刘辉","江西")));
        singleLink.printLink();
        //---查---
        System.out.println(singleLink.get(2).getData().toString());
        System.out.println(singleLink.getAfter(3).getData().toString());
        System.out.println(singleLink.getAfter(1).getData().toString());
        System.out.println(singleLink.getAfterTwo(5).getData().toString());
        System.out.println(singleLink.get(new Node<>(wpUser)));
        System.out.println(singleLink.getLast().getData());
        //---删---
        singleLink.afterInsert(new Node<>(new User(32,"陈志威","厦门")));
        singleLink.afterInsert(new Node<>(new User(33,"王洋","内蒙古")));
        singleLink.afterInsert(new Node<>(new User(34,"疯子","海南")));
        singleLink.afterInsert(new Node<>(new User(35,"二狗","北京")));
        singleLink.printLink();
        singleLink.headDelete();
        singleLink.printLink();
        singleLink.afterDelete();
        singleLink.printLink();
        singleLink.delete(new Node<>(new User(33,"王洋","内蒙古")));
        singleLink.printLink();
    }
}
