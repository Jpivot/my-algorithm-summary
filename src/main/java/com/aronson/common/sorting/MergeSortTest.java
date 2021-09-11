package com.aronson.common.sorting;

import java.util.Arrays;
import java.util.Collections;

/**
 * 归并排序
 * 参考链接：https://www.cnblogs.com/chengxiao/p/6194356.html
 */
public class MergeSortTest {
    public static void main(String[] args) {
        int[] nums = {11, 2, 55, 8, 6, 13, 4, 3, 0};//{8, 7, 6, 5, 4, 3, 2, 1, 191};//{8, 4, 5, 7, 1, 3, 6, 2};//{11, 2, 55, 8, 6,13,4,3,0};
        sort(nums);
        Collections.singletonList(nums).forEach(num -> System.out.print(Arrays.toString(num) + " "));
    }

    static void sort(int[] nums) {
        // 为了避免在递归过程中频繁申请内存资源
        int[] tmpNums = new int[nums.length];
        doSort(nums, 0, nums.length - 1, tmpNums);
    }

    static void doSort(int[] nums, int low, int high, int[] tmpNums) {
        if (low < high) {
            int mid = (low + high) / 2;
            doSort(nums, low, mid, tmpNums);
            doSort(nums, mid + 1, high, tmpNums);
            merge(nums, low, mid, high, tmpNums);
        }
    }

    static void merge(int[] nums, int low, int mid, int high, int[] tmpNums) {
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (k < tmpNums.length && i <= mid && j <= high) {
            if (nums[i] > nums[j]) {
                tmpNums[k++] = nums[j++];
            } else {
                tmpNums[k++] = nums[i++];
            }
        }
        while (i <= mid) {
            tmpNums[k++] = nums[i++];
        }
        while (j <= high) {
            tmpNums[k++] = nums[j++];
        }
        // 需要将临时数组中的变量复制到原来的数组中
        for (k = 0, i = low; i <= high; k++, i++) {
            nums[i] = tmpNums[k];
        }
    }
}
