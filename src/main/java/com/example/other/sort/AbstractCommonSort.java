package com.example.other.sort;

import java.util.Arrays;

/**
 * @author huangqi
 * @Package com.example.other.sort
 * @Description:
 * @date 2020/4/20 5:56 下午
 */
public abstract class AbstractCommonSort {
    /**
     * 具体排序 时间复杂度取平均时间复杂度
     *
     * @param arrays
     * @return
     */
    abstract int[] sort(int[] arrays);

    /**
     * 交换数值
     *
     * @param arrays
     * @param i
     * @param j
     */
    public static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    /**
     * 打印
     *
     * @param arrays
     */
    public void outPrint(int[] arrays) {
        System.out.println("排序前：" + Arrays.toString(arrays));
        System.out.println("排序后：" + Arrays.toString(sort(arrays)));
    }
}
