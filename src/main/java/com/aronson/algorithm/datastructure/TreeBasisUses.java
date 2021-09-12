package com.aronson.algorithm.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: Sherlock
 */
public class TreeBasisUses {

    /**
     * 中序遍历非递归实现
     *
     * @param root
     */
    public static void inorderTraversalByStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        // 外层做总的非空判断
        while (root != null || !stack.isEmpty()) {
            // 里层做取左子树的非空判断
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    /**
     * 计算高度
     *
     * @param node
     * @return
     */
    public int calculateHeight(TreeNode node) {
        if (node != null) {
            int left = calculateHeight(node.left);
            int right = calculateHeight(node.right);
            return Math.max(left, right) + 1;
        }
        return 0;
    }

    /**
     * 判断树的形态（含值）是否相同
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public static void levelOrderForeach(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while ((root = queue.poll()) != null) {
            System.out.println(root.val);
            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }
        }
    }

    /**
     * 求树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        // 这里其实可以用Math.max来求得最大值，这样就节省空间的使用
        int depth = 0;
        int tmpLeft = 0;
        int tmpRight = 0;
        if (root != null) {
            tmpLeft = maxDepth(root.left);
            tmpRight = maxDepth(root.right);
            depth = tmpLeft > tmpRight ? tmpLeft + 1 : tmpRight + 1;
        }
        return depth;
    }
}
