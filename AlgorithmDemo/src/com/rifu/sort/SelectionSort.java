package com.rifu.sort;

import java.util.Random;

/**
 * @author Rifu
 * @create 2018-08-28 16:20
 * 选择排序
 * 1.初始状态：无序区为R[1..n]，有序区为空；
 * 2.第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 * 3.n-1趟结束，数组有序化了。
 */
public class SelectionSort {
    static int[] arr;

    public static void main(String [] args){
        initData();
        printArr();
        selectionSort();
        printArr();
    }

    public static void initData(){
        arr=new int[20];
        for(int i =0;i<arr.length;i++){
            arr[i]=new Random().nextInt(100);
        }
    }

    public static void printArr(){
        for(int i =0 ;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }

    public static void selectionSort(){
        int len=arr.length,minIndex,temp;
        for(int i=0;i<len - 1;i++){
            minIndex=i;
            for(int j=i+1;j<len;j++){
                if(arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }

            temp= arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
    }
}
