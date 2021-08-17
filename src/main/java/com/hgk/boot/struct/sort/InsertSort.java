package com.hgk.boot.struct.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 1.简单插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {6, -3, 38, -21, 87, 21, 49};
        System.out.println("原数组：" + Arrays.toString(arr));
        //冒泡排序（升序）
        insertSort(arr);
        System.out.println("简单插入排序（升序）：" + Arrays.toString(arr));
    }

    /**
     * 简单插入排序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for(int i = 1;i<arr.length;i++){
            int temp = arr[i];
            int j;
            for(j= i-1;j>=0;j--){
                if(temp < arr[j]){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            if(j+1 != i){
                arr[j+1] = temp;
            }
        }
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void shellSort(int[] arr){
        
    }
}
