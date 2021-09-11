package com.aronson.algorithm.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueTest {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            // 如果是o1-o2，则poll的时候是从小到大
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10, comparator);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            priorityQueue.add(random.nextInt(100));
        }
        System.out.println(priorityQueue.toString());
        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(priorityQueue.poll());
        }
    }

    /**
     * 优先队列求滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        int[] numsK = new int[length - k + 1];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] != o1[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            priorityQueue.add(new int[]{nums[i], i});
        }
        numsK[0] = priorityQueue.peek()[0];
        for (int i = k; i < length; i++) {
            priorityQueue.add(new int[]{nums[i], i});
            while (priorityQueue.peek()[1] < i - k + 1) {
                priorityQueue.poll();
            }
            numsK[i - k + 1] = priorityQueue.peek()[0];
        }
        return numsK;
    }

}
