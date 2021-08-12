package com.hgk.boot.struct.recursion;

/**
 * 递归
 */
public class Recursion {

    public static void main(String[] args){
//        test(5);
        //5!=1*2*3*4*5
        System.out.println(Integer.valueOf(fav(5)));
    }

    public static void test(int n){
        if(n >2){
            test(n-1);
        }else{
            System.out.printf("n = %d\n",n);
        }
    }

    /**
     * 求n的阶乘 n! = (n-1)!*n;
     * @param n
     * @return
     */
    public static int fav(int n){
        if(n == 1){
            return 1;
        }else{
            return fav(n-1) * n;
        }
    }
}
