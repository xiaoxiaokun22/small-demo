package com.hgk.boot.struct.sort;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 交换排序
 * 1.冒泡排序
 * 2.快速排序
 */
public class JiaohuanSort {
    public static void main(String[] args) {
        int[] arr = {6, -3, 38, -21, 87, 21, 49};
        System.out.println("原数组：" + Arrays.toString(arr));
        //冒泡排序（升序）
        maopaoSort(arr);
        System.out.println("冒泡排序（升序）：" + Arrays.toString(arr));
        //快速排序（升序）
        Integer[] arr2 = {6, -3, 38, -21, 87, 21, 49};
        fastSort2(arr2,0,arr2.length-1);
        System.out.println("快速排序（升序）：" + Arrays.toString(arr2));
    }

    /**
     * 冒泡排序（升序）
     * 关键点：
     * 1.确认循环的次数,每循环n次，最后n位元素就确定了：n < length-1
     * 2.确认每次循环到那个元素的下标(因为这里是与arr[i+1]比较)：i < length-n-1
     * 3.优化：如果在一次循环中一次交换都没有，说明已经排好了，结束循环。
     * @param arr
     * @return
     */
    private static void maopaoSort(int[] arr) {
        int length = arr.length;
        boolean flag = false;
        for(int n=0;n<length-1;n++){
            for(int i=0;i<length-n-1;i++){
                if(arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    flag = true;
                }
            }
            if(flag)
                flag = false;
            else
                break;
        }
    }

    /**
     * 快速排序（升序）
     * 步骤：
     * 1.随机取一个元素作为中间数center，这里取第一个元素（那么要先从右指针开始找，找到后覆盖第一个元素）
     * 2.左移right指针找到第一个小于temp的元素，放到left指针处，left右移（此时right元素空了，其实是元素值没有意义了）
     * 3.右移left指针找到第一个大于temp的元素，放到right指针出，right左移
     * 4.当left==right时，就是temp值的位置，结束一次循环。
     * 5.递归左右两边的数组，重复上面操作。
     * @param arr
     * @param left
     * @param right
     */
    public static void fastSort2(Integer[] arr, int left, int right){
        int l = left;
        int r = right;
        if(l >= r)
            return;
        Integer center = arr[left];
        while (l != r){
            while (arr[r]>center && l<r) r--;
            if(r >l){
               arr[l] = arr[r];
               l++;
            }
            while (arr[l]<center && l<r) l++;
            if(r >l){
                arr[r] = arr[l];
                r--;
            }
        }
        if(l == r){
            arr[l] = center;
        }
        fastSort2(arr,left,l-1);
        fastSort2(arr,l+1,right);

    }
}