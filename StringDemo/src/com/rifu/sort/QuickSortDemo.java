package com.rifu.sort;

/**
 * @author Rifu
 * @create 2018-06-12 17:20
 * <p>
 * 快速排序：
 * 从待排的数据序列中任取一个数据（如第一个数据）作为基准值，所有比它小的元素放到左边，
 * 所有比它大的元素放到右边。经过这样一趟下来，该序列形成左右两个子序列，
 * 左边序列中的数据元素的值都比基准值小，右边序列中数据元素的值都比基准值大。
 * 接下来对左右两个子序列进行递归排序。
 */
public class QuickSortDemo {

    public static void main(String[] args) {
        int [] list=new int[]{12 ,5,3,6,9,15,24,22,22};
        print(list);
        quickSort(list,0,list.length-1);
        print(list);
    }
    public static void print(int [] data){
        for (int i:data){
            System.out.print(i+"\t");
        }
        System.out.println();
    }

    public static void quickSort(int[] list, int begin, int end) {
        if (begin >= end)
            return;
        int point = list[begin];
        int i = begin + 1;
        int j = end;
        while (i<j) {
            while (i < end && list[i] < point) {
                i++;
            }
            while (j>begin && list[j]>point){
                j--;
            }
            if(i<j){
                swap(list,i,j);
            }
        }
        swap(list,begin,j);
        quickSort(list,begin,j-1);
        quickSort(list,j+1,end);
    }

    public static void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }
}
