package com.example.other.sort;

import java.util.Arrays;

/**
 * @author huangqi
 * @Package com.example.other.sort
 * @Description: 冒泡排序
 * @date 2020/4/20 5:17 下午
 */
public class BubbleSort {

    /**
     * 普通方法 时间复杂度O(n^2)
     */
    public static int[] sort(int[] arrays) {
        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = i + 1; j < arrays.length - 1; j++) {
                if (arrays[i] > arrays[j]) {
                    swap(arrays, i, j);
                }
            }
        }
        return arrays;
    }

    private static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 0, 2, 5};
        System.out.println("排序前：" + Arrays.toString(array));
        System.out.println("排序后：" + Arrays.toString(sort(array)));
    }
}
