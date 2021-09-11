package com.aronson.algorithm.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] nums = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
//        int[] nums = {6,6,6,6,6,6,6,6,6,6,7,6,6};
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()).toString());
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()).toString());
    }

    /**
     * @param nums
     * @param left
     * @param right
     * @return 枢纽点
     */
    public static int partition(int[] nums, int left, int right) {
        int i = left;
        int j = right;
        int temp = nums[i];
        while (i < j) {
            // 这里不取nums[j] >= temp，是为了避免如果数组元素全部相同的极端情况下，
            // 枢纽点两边长度不均衡，导致算法性能为O（N^2），但在该极端情况下，
            // 会做无谓的交换，因此算法不稳定
            while (i < j && nums[j] > temp) {
                j--;
            }
            // 这么限定是i和j会相遇，相遇点即为pivot
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            // 重复限制i < j没有毛病，如果不限制，则i会一直加，导致越界
            while (i < j && nums[i] < temp) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = temp;
        return i;
    }

    /**
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSort(int[] nums, int left, int right) {
        // 如果left和right相等，或者pivot就是left或者right，那么在递归的时候，
        // 就会出现left大于等于right情况
        if (left < right) {
            int pivot = partition(nums, left, right);
            quickSort(nums, left, pivot - 1);
            quickSort(nums, pivot + 1, right);
        }
    }
}
