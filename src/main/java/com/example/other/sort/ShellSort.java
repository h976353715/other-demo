package com.example.other.sort;

/**
 * @author huangqi
 * @Package com.example.other.sort
 * @Description: 希尔排序 对插入排序的优化
 * @date 2020/4/21 11:09 上午
 */
public class ShellSort extends AbstractCommonSort {

    /**
     * 希尔排序 O(n^1.3)
     * <p>
     * 1 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 2 按增量序列个数k，对序列进行k 趟排序；
     * 3 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @param arrays
     * @return
     */
    @Override
    int[] sort(int[] arrays) {
        //增量gap
        for (int gap = arrays.length / 2; gap > 0; gap /= 2) {
            //对每个块进行直接插入排序
            for (int i = gap; i < arrays.length; i++) {
                int j = i;
                int current = arrays[i];
                while (j - gap >= 0 && current < arrays[j - gap]) {
                    arrays[j] = arrays[j - gap];
                    j = j - gap;
                }
                arrays[j] = current;
            }
        }
        return arrays;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 0, 2, 5};
        new ShellSort().outPrint(array);
    }
}
