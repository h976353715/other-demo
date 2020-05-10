package com.example.other.sort;

/**
 * @author huangqi
 * @Package com.example.other.sort
 * @Description: 直接插入排序
 * @date 2020/4/20 5:55 下午
 */
public class InsertionSort extends AbstractCommonSort {

    /**
     * 插入排序 时间复杂度O(n^2) 稳定
     * 1 从第一个元素开始，该元素可以认为已经被排序；
     * 2 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5 将新元素插入到该位置后；
     * 6 重复步骤2~5。
     * @param arrays
     * @return
     */
    @Override
    int[] sort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int preIndex = i - 1;
            int temp = arrays[i];
            while (preIndex >= 0 && arrays[preIndex] > temp) {
                arrays[preIndex + 1] = arrays[preIndex];
                preIndex--;
            }
            arrays[preIndex + 1] = temp;
        }

        return arrays;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 0, 2, 5};
        new InsertionSort().outPrint(array);
    }
}
