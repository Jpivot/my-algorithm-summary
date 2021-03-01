package binarytree;

import java.util.*;

/**
 * 树的层次遍历
 * 剑指 Offer 32 - II
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 */
public class TreeLevelOrderTest {
    /**
     * 适合打印
     *
     * @param root
     */
    public static void levelOrderPrint(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.print(root.val + " ");
            if (root.left != null) queue.add(root.left);
            if (root.right != null) queue.add(root.right);
        }
    }

    /**
     * 储存每一层的节点值
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode treeNode = queue.poll();
                tmp.add(treeNode.val);
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            result.add(tmp);
        }
        return result;
    }

    /**
     * 层次遍历，偶数层翻转
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderReverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        int levelCount = 0;
        while (!queue.isEmpty()) {
            levelCount++;
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode treeNode = queue.poll();
                tmp.add(treeNode.val);
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            if (levelCount % 2 == 0) {
                // 调用工具翻转链表
                Collections.reverse(tmp);
            }
            result.add(tmp);
        }
        return result;
    }

    public static void main(String args[]) {
        TreeNode temp7 = new TreeNode(7);
        TreeNode temp2 = new TreeNode(2);
        TreeNode temp11 = new TreeNode(11);
        temp11.left = temp7;
        temp11.right = temp2;
        TreeNode temp4 = new TreeNode(4);
        temp4.left = temp11;
        TreeNode temp5 = new TreeNode(5);
        temp5.left = temp4;
        TreeNode temp8 = new TreeNode(8);
        temp5.right = temp8;
        TreeNode temp13 = new TreeNode(13);
        temp8.left = temp13;
        TreeNode temp4copy = new TreeNode(4);
        temp8.right = temp4copy;
        TreeNode temp1 = new TreeNode(1);
        temp4copy.right = temp1;
        TreeNode root = temp5;
        List<List<Integer>> list = levelOrderReverse(root);
        System.out.println(list.toString());
    }
}
