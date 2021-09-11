package com.aronson.list;

import java.util.Stack;

public class ReverseListTest {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode listNode = new ListNode();
        traverse(head, listNode);
        return listNode.next;
    }

    public ListNode traverse(ListNode head, ListNode listNode) {
        if (head != null && head.next != null) {
            ListNode tmp = traverse(head.next, listNode);
            tmp.next = head;
            listNode = tmp.next;
            head.next = null;
        } else if (head != null) {
            listNode.next = head;
            listNode = listNode.next;
        }
        return listNode;
    }

    public ListNode reverseListByStack(ListNode head) {
        ListNode listNode = new ListNode();
        ListNode listNode1 = listNode;
        Stack<ListNode> stack = new Stack<>();
        for (; head != null; head = head.next) {
            stack.push(head);
        }
        while (!stack.isEmpty()) {
            ListNode tmp = stack.pop();
            listNode1.next = tmp;
            tmp.next = null;
            listNode1 = listNode1.next;
        }
        return listNode.next;
    }
}
