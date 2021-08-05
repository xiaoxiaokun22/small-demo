package com.hgk.boot.test;

import java.util.Arrays;

public class StaticTest {
    public Integer age;
    public String name;
    public static String address = "china";

    public static String scope;

    /*
     * 静态代码块作用
     * 执行顺序 (静态代码块—>非静态代码块—>构造方法)
     * 1.用于给类进行初始化，初始化就是给属性赋值
     * 2.一般用于加载mysql驱动
     * 3.如果有一段代码从始至终，只运行一次，可以写到静态代码块
     */
    static {
        System.out.println("===静态代码块1 start===");
        System.out.println(address);
        System.out.println(scope);
//        System.out.println(address2);//会编译错误 java: 非法前向引用
        System.out.println("===静态代码块1 end===");
    }
//    public static String address2 = "china2";

    /**
     * 非静态代码块
     * 1.每new一次对象，都会执行非静态代码块
     */
    {
        System.out.println("===非静态代码块===");
    }

    public StaticTest(){
        System.out.println("===无参构造方法===");
    }

    public StaticTest(Integer age, String name){
        this.age = age;
        this.name = name;
        System.out.println("===构造方法===");
    }

    public String getName(){
        System.out.println("===getName方法===");
        return this.name;
    }

    public static void setScope(String scope){
        StaticTest.scope = scope;
        System.out.println("===设置静态成员变量scope===");
    }

    static {
        System.out.println("===静态代码块2===");
    }
}
