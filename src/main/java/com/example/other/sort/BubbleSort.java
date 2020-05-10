package com.example.other.sort;

/**
 * @author huangqi
 * @Package com.example.other.sort
 * @Description: 冒泡排序
 * @date 2020/4/20 5:17 下午
 */
public class BubbleSort extends AbstractCommonSort {

    /**
     * 普通方法 时间复杂度O(n^2) 稳定
     * 1 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3 针对所有的元素重复以上的步骤，除了最后一个；
     * 4 重复步骤1~3，直到排序完成。
     */
    @Override
    public int[] sort(int[] arrays) {
        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = i + 1; j < arrays.length - 1; j++) {
                if (arrays[i] > arrays[j]) {
                    swap(arrays, i, j);
                }
            }
        }
        return arrays;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 0, 2, 5};
        new BubbleSort().outPrint(array);
    }
}
