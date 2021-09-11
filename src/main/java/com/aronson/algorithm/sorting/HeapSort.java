package com.aronson.algorithm.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * @author Sherlock
 */
public class HeapSort {

    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;

        // 建立起一个大根堆
        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            // 每次最大的放最后，接着长度递减一再调整，这样就得到升序排列的数组
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        HeapSort heapSort = new HeapSort();
        int[] heapSortNums = heapSort.sort(nums);
        System.out.println(Arrays.stream(heapSortNums).boxed().collect(Collectors.toList()).toString());
    }

}
