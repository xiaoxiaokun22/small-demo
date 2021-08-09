package com.hgk.boot.struct.sparse_array;

/**
 * 稀疏数组与二维数组的互相转换
 * 0 0 0 0           3 4 2
 * 0 9 0 0    =>     1 1 9
 * 18 0 0 0          2 0 18
 */
public class SparseArray {

    public static void main(String[] args) {
        int m = 3;
        int n = 4;
        int[][] arr = new int[m][n];
        arr[1][1] = 2;
        arr[2][0] = 18;
        System.out.println("原数组：");
        printArray(arr);
        System.out.println("转化为稀疏数组：");
        int[][] sparseArray =  arrayToSparseArray(arr, m, n);
        printArray(sparseArray);
        System.out.println("恢复为原数组：");
        int[][] backArr =  sparseArrayToArray(sparseArray);
        printArray(backArr);
    }
    /**
     * 打印输出二维数组
     */
    public static void printArray(int[][] arr){
        for(int[] row: arr){
            for(int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }

    /**
     * 二维数组转稀疏数组
     * @param arr 原数组
     * @param m 原数组行数
     * @param n 原数组列数
     */
    public static int[][] arrayToSparseArray(int[][] arr, int m, int n){
        int validCount =  getValidCount(arr);
        int[][] sparseArray = new int[validCount + 1][3];
        //稀疏数组第一行
        sparseArray[0][0] = m;
        sparseArray[0][1] = n;
        sparseArray[0][2] = getValidCount(arr);
        //存储有效数据
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = arr[i][j];
                }
            }
        }
        return sparseArray;
    }

    /**
     * 计算数组的有效数据大小
     */
    public static int getValidCount(int[][] arr){
        int sum = 0;
        for (int[] row: arr){
            for(int data: row){
                if(data != 0){
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 稀疏数组转二维数组
     */
    public static int[][] sparseArrayToArray(int[][] sparseArray){
        int m = sparseArray[0][0];
        int n = sparseArray[0][1];
        int validCount = sparseArray[0][2];
        int[][] arr = new int[m][n];
        for(int i = 1;i<sparseArray.length;i++){
            arr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        return arr;
    }
}
