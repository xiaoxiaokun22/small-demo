package com.hgk.boot.struct.single_link_circle;

import com.hgk.boot.struct.double_link_head.DoubleHeadLinkDemo;
import lombok.Data;

/**
 * 单向循环链表（不带头节点）
 * 应用场景：约瑟夫问题，产生一个出队编号的序列
 */
public class SingleCircleLinkDemo {

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
        SingleCircleLink link = new SingleCircleLink();
        //---增---
        link.insert(new User(1,"郝国坤","山西"));
        link.insert(new User(41,"李雷","内蒙古"));
        link.insert(new User(25,"王鹏","上海"));
        link.insert(new User(82,"白棋","南京"));
        link.insert(new User(9,"王杨","北京"));
        link.printLink();
        //---约瑟夫---
        //82-41-1
        link.yuesefu(2,3);
        
    }
}
