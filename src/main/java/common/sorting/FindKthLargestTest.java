package common.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 寻找第k大的值
 */
public class FindKthLargestTest {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        // 去掉k-1个最大值，剩下的调整完，堆顶就是第k个最大值
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            // 大根堆最大值是堆顶元素，即数组第一个元素，跟i做交换，即删除该堆最大值
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    /**
     * 建立起一个大根堆
     *
     * @param a
     * @param heapSize
     */
    public void buildMaxHeap(int[] a, int heapSize) {
        // heapsize除以2，是为了找到最后一个有子节点的节点
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    /**
     * 调整堆，保证以parent为根节点的子树，一定是一棵大根堆树
     *
     * @param a
     * @param parent
     * @param heapSize
     */
    public void maxHeapify(int[] a, int parent, int heapSize) {
        // 这里的i就是left和right的父节点
        // 下面的left和right的计算关系是当下标从0开始时，与父节点的关系
        int left = parent * 2 + 1, right = parent * 2 + 2, largest = parent;
        if (left < heapSize && a[left] > a[largest]) {
            largest = left;
        }
        if (right < heapSize && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != parent) {
            // 在没有交换下，以largest为根对应的树是已经调整好的，
            // largest下标对应的值比parent大，交换过后，状态被改变，
            // 需要重新调整
            swap(a, parent, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        // 堆，按数组前后顺序，按层次建立起一棵二叉树
        //            3
        //          /   \
        //         2     3
        //        / \   / \
        //       1  2  4   5
        //      / \
        //     5   6
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        FindKthLargestTest findKthLargestTest = new FindKthLargestTest();
        findKthLargestTest.buildMaxHeap(nums, nums.length);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()).toString());
    }

}
