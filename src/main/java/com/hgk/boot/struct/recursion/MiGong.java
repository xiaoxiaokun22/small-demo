package com.hgk.boot.struct.recursion;

import java.util.Arrays;

/**
 * 递归解决迷宫问题
 */
public class MiGong {
    public static void main(String[] args){
        //新建一个迷宫地图
        int[][] arr = new int[6][5];
        for(int i = 0;i<5;i++){
            arr[0][i] = 1;
            arr[5][i] = 1;
        }
        for(int j = 0;j< 6;j++){
            arr[j][0] = 1;
            arr[j][4] = 1;
        }
        arr[3][1] = 1;
        arr[3][2] = 1;
        arr[4][4] = 0;//目的地
        System.out.println("原地图：");
        printMigong(arr);
        //开始走
        startMigong(arr,1,1);
        //输出路线
        System.out.println("输出路线：");
        printMigong(arr);
    }

    /**
     * 开始迷宫
     * 策略：下右上左
     * @param arr
     * @param i 例如："1,1"  英文逗号分割
     * @param j "4,4"
     */
    private static boolean startMigong(int[][] arr, int i,int j) {
        //目的地坐标, 写死
        int destI = 4;
        int destJ = 4;
        if(arr[destI][destJ] == 2){
            return true;
        }else{
            if(arr[i][j] == 0){
                arr[i][j] = 2;//走过可以走
                if(startMigong(arr,i+1,j)){
                    return true;
                }else if(startMigong(arr,i,j+1)){
                    return true;
                }else if(startMigong(arr,i-1,j)){
                    return true;
                }else if(startMigong(arr,i,j-1)){
                    return true;
                }else {
                    //死路
                    arr[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    private static void printMigong(int[][] arr) {

        for(int i =0;i<6;i++){
            for(int j = 0;j<5;j++){
                System.out.printf("%d ",arr[i][j]);
            }
            System.out.println();
        }
    }

}
