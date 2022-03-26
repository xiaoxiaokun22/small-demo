package com.hgk.boot.collections;

import cn.hutool.core.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.*;

/**
 * 对集合排序
 */
public class Demo {
    private static Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args){
//        test01();
        test02();
    }

    /**
     * 1.Collections.sort(list) 默认按升序（ascii）排序
     * 2.Collections.sort(list,new Comparator<>)
     * Java的Comparator升序降序的记法(https://zhuanlan.zhihu.com/p/54004622)：
     * o1是前面的元素，o2是后面的元素，返回值>1是交换位置，否则不交换。
     */
    public static void test01(){
        List<Integer> list = new ArrayList<>();
        list.add(58);
        list.add(26);
        list.add(71);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        List<String> list2 = new ArrayList<>();
        list2.add("bua");
        list2.add("tnj");
        list2.add("aie");
        System.out.println(list2);
//        Collections.sort(list2);
//        System.out.println(list2);

        Collections.sort(list2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list2);

    }

    /**
     * 按年龄先升序，score降序
     */
    public static void test02(){
        List<User> list = new ArrayList<>();
        list.add(new User("libai",60,162));
        list.add(new User("dufu",62,172));
        list.add(new User("angpeng",25,180));
        list.add(new User("xiaoxi",25,158));

//        list.sort((c1,c2)->{
//            int a = c1.getAge() - c2.getAge();
//            if(a > 0){
//                return 1;
//            }else if(a < 0){
//                return -1;
//            }else{
//                int b = c1.getScore() - c2.getScore();
//                if(b > 0){
//                    return -1;
//                }else{
//                    return 1;
//                }
//            }
//        });
//        list.stream().forEach((r)->System.out.println(r.toString()));

        list.sort(Comparator.comparing(User::getAge).thenComparing(User::getScore,Comparator.reverseOrder()));
        list.stream().forEach((r)->System.out.println(r.toString()));
        CollectionUtil.isEmpty(list);
        DispatcherServlet
    }

}
