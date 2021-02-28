package common.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6, 0};
//        // 插入排序，类似冒泡，前n-1个已经排好序了。第n个数往前交换
//        // i用来指示已经排好序的部分
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j > 0; j--) {
//                if (nums[j] < nums[j - 1]) {
//                    int temp = nums[j];
//                    nums[j] = nums[j - 1];
//                    nums[j - 1] = temp;
//                } else {
//                    // 因为前n-1个已经排好序了
//                    break;
//                }
//            }
//        }
//        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()).toString());

        int[] arr = {7, 6, 9, 3, 1, 5, 2, 4};
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 这里相当于插入排序中的第n个值
                int tmp = arr[i];
                // 从后往前，隔gap进行值的比较，实际也就是插入排序的思想，默认前面的数已经排列完了
                for (j = i; j >= gap && tmp - arr[j - gap] < 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
        System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()).toString());
    }

    /**
     * 冒泡排序
     *
     * @param nums
     */
    public static void BubbleSort(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            boolean exchangeFlag = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    exchangeFlag = true;
                }
            }
            if (!exchangeFlag) {
                break;
            }
        }
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()).toString());
    }
}
