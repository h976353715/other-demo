package com.example.other.sort;

/**
 * @author huangqi
 * @Package com.example.other.sort
 * @Description: 选择排序
 * @date 2020/4/20 5:40 下午
 */
public class SelectionSort extends AbstractCommonSort {


    /**
     * 选择排序 不稳定 实在复杂度O(n^2)
     *
     * 1 初始状态：无序区为R[1..n]，有序区为空；
     * 2 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * 3 n-1趟结束，数组有序化了。
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 0, 2, 5};
        new SelectionSort().outPrint(array);
    }
}
