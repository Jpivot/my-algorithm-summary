package com.aronson.algorithm.heap;

import java.util.HashMap;

import static com.aronson.algorithm.heap.TreeLevelOrderTest.levelOrder;

/**
 * 第105题 前序遍历和中序遍历的结果还原一棵二叉树
 */
public class BuildTreeTest {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 定位中序排列数组的各个元素的位置
        HashMap<Integer, Integer> hashMap = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }
        TreeNode treeNode = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1,
                hashMap);
        return treeNode;
    }

    public static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder,
                                     int inStart, int inEnd, HashMap<Integer, Integer> hashMap) {
        // 递归结束条件，不然会出现溢出
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode node = new TreeNode(preorder[preStart]);
        int rootIndex = hashMap.get(preorder[preStart]);
        node.left = buildTree(preorder, preStart + 1, preStart + rootIndex - inStart, inorder, inStart, rootIndex - 1, hashMap);
        node.right = buildTree(preorder, preStart + rootIndex - inStart + 1, preEnd, inorder, rootIndex + 1, inEnd, hashMap);
        return node;
    }

    public static void main(String[] args) throws InterruptedException {
//        TreeNode node7 = new TreeNode(7);
//        TreeNode nodeNegative8 = new TreeNode(-8);
//        TreeNode node9 = new TreeNode(9);
//        node7.left = nodeNegative8;
//        node7.right = node9;
//        TreeNode nodeNegative2 = new TreeNode(-2);
//        TreeNode node3 = new TreeNode(3);
//        node9.left = nodeNegative2;
//        node9.right = node3;
//        TreeNode nodeAgain9 = new TreeNode(9);
//        TreeNode node11 = new TreeNode(11);
//        nodeNegative2.left = nodeAgain9;
//        nodeNegative2.right = node11;
//        TreeNode node21 = new TreeNode(21);
//        TreeNode node51 = new TreeNode(51);
//        node3.right = node21;
//        node3.left = node51;
//        System.out.println(levelOrder(node7).toString());
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode node = buildTree(preorder, inorder);
        System.out.println(levelOrder(node).toString());

    }
}
