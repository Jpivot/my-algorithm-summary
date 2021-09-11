package com.aronson.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author Sherlock
 */
public class MaxSlidingTest {
    public static void main(String[] args) {
        int[] nums = {8, 7, 6, 3, 18, 67, 3, 9, 1, 4, 89, 22};
        int[] numsK = maxSlidingNum(nums, 3);
        System.out.println(Arrays.stream(numsK).boxed().collect(Collectors.toList()));

    }

    /**
     * 用双端队列来求滑动窗口最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingNum(int[] nums, int k) {
        int length = nums.length;
        int[] numsK = new int[length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        numsK[0] = nums[deque.peekFirst()];
        for (int i = k; i < length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            numsK[i - k + 1] = nums[deque.peekFirst()];
        }
        return numsK;
    }
}
