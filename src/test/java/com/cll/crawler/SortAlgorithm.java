package com.cll.crawler;

/**
 * @author chenliangliang
 * @date: 2017/12/19
 */
public class SortAlgorithm {

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] maoPao(int... arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 改进冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] maoPaoPlus(int... arr) {
        int n = arr.length;
        int i = n - 1;
        int pos;
        while (i > 0) {
            pos = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    pos = j;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            i = pos;
        }
        return arr;
    }


    /**
     * 计数排序
     *
     * @param arr
     * @return
     */
    public static int[] countSort(int... arr) {
        int min = arr[0];
        int max = arr[0];
        for (int a : arr) {
            min = Math.min(a, min);
            max = Math.max(a, max);
        }
        int d = max - min + 1;
        int[] temp = new int[d];
        for (int a : arr) {
            temp[a - min] = 1;
        }
        int count = 0;
        for (int i = 0; i < d; i++) {
            if (temp[i] == 1) {
                arr[count] = i + min;
                count++;
            }
        }
        return arr;
    }


    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int... arr) {
        int n = arr.length;
        int gap = n / 2;
        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i - gap; j >= 0 && temp < arr[j]; j = j - gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = temp;
            }
            gap = gap / 2;
        }
        return arr;
    }
}
