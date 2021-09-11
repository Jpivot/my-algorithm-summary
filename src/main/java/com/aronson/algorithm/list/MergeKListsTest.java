package com.aronson.algorithm.list;

/**
 * 合并k个有序链表，第23题
 */
public class MergeKListsTest {
    public static void main(String[] args) {
//        new MergeKListsTest().mergeKLists()
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int left = 0;
        int right = lists.length;
        return doMergeKLists(lists, left, right - 1);
    }

    public ListNode doMergeKLists(ListNode[] lists, int left, int right) {
        if (lists.length == 0) return null;
        if (left < right) {
            int mid = (left + right) / 2;
            ListNode leftListNode = doMergeKLists(lists, left, mid);
            ListNode rightListNode = doMergeKLists(lists, mid + 1, right);
            return merge(leftListNode, rightListNode);
        }
        return lists[left];
    }

    public ListNode merge(ListNode leftListNode, ListNode rightListNode) {
        ListNode head = new ListNode(-1, null);
        ListNode head1 = head;
        while (leftListNode != null && rightListNode != null) {
            if (leftListNode.val < rightListNode.val) {
                head.next = leftListNode;
                leftListNode = leftListNode.next;
                head = head.next;
            } else {
                head.next = rightListNode;
                rightListNode = rightListNode.next;
                head = head.next;
            }
        }
        if (leftListNode != null) {
            head.next = leftListNode;
        }
        if (rightListNode != null) {
            head.next = rightListNode;
        }
        return head1.next;

    }
}
