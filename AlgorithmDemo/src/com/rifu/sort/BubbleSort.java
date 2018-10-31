package com.rifu.sort;

import java.util.Random;

/**
 * @author Rifu
 * @create 2018-08-28 16:12
 * 冒泡排序
 * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 3.针对所有的元素重复以上的步骤，除了最后一个；
 * 4.重复步骤1~3，直到排序完成。
 */
public class BubbleSort {

    static int[] arr;

    public static void main(String [] args){
        initData();
        printArr();
        bubbleSort();
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

    public static void bubbleSort(){
        int len=arr.length;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len- 1 -i;j++){
                if(arr[j]>arr[j+1]){
                    int temp =arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
}
