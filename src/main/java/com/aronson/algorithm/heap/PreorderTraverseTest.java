package com.aronson.algorithm.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Sherlock
 * @date 2021/2/28
 */
public class PreorderTraverseTest {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraverse(root, list);
        return list;
    }

    public void preorderTraverse(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorderTraverse(root.left, list);
            preorderTraverse(root.right, list);
        }
    }

    /**
     * 迭代法前序遍历
     *
     * @param root
     * @param list
     */
    public void preorderTraverseByIteration(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            // 只要是pop出来的，就直接去找右节点，并且指向右节点了
            //    5
            //  /   \
            // 6     9
            //  \
            //   7
            // 如上，6如果pop出来了，直接找到右节点7，之后栈底就是5了，不会再有6出现了
            root = stack.pop();
            root = root.right;
        }
    }
}
