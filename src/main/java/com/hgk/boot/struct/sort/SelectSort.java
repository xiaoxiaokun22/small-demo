package com.hgk.boot.struct.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 1.简单选择排序
 * 2.堆排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {6, -3, 38, -21, 87, 21, 49};
        System.out.println("原数组：" + Arrays.toString(arr));
        //简单选择排序（升序）
        selectSort(arr);
        System.out.println("简单选择排序（升序）：" + Arrays.toString(arr));
        //堆排序（升序）

    }

    /**
     * 简单选择排序
     * 1.从第一个数开始，循环数组，循环完后选择一个最小数放到第一个位置。
     *  下次循环从第二数个开始，选择后放到第二个位置。重复循环。
     * @param arr
     */
    private static void selectSort(int[] arr) {
        for(int n=0;n < arr.length-1;n++){
            int low = n;
            for(int i=n+1;i < arr.length;i++){
                if(arr[i]<arr[low]){
                    low = i;
                }
            }
            if(n != low){
                int temp = arr[n];
                arr[n] = arr[low];
                arr[low] = temp;
            }
        }
    }


}
