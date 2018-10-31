package com.rifu.sort;

import java.util.Random;

/**
 * @author Rifu
 * @create 2018-08-28 16:25
 * 插入排序
 * 1.从第一个元素开始，该元素可以认为已经被排序；
 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 5.将新元素插入到该位置后；
 * 6.重复步骤2~5。
 */
public class InsertionSort {
    static int[] arr;

    public static void main(String[] args) {
        initData();
        printArr();
        insertionSort();
        printArr();
    }

    public static void initData() {
        arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(100);
        }
    }

    public static void printArr() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    public static void insertionSort() {
        int len = arr.length, preIndex, current;
        for (int i = 0; i < len-1; i++) {
            preIndex = i;
            current = arr[i+1];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex+1]=arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1]=current;
        }
    }
}
