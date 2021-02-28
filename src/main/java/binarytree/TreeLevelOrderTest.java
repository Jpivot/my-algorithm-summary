package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树的层次遍历
 * 剑指 Offer 32 - II
 */
public class TreeLevelOrderTest {
    /**
     * 适合打印
     *
     * @param root
     */
    public static void levelOrderPrint(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        while (root != null || !queue.isEmpty()) {
            if (!queue.isEmpty()) {
                root = queue.poll();
                System.out.print(root.val + " ");
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
                // 如果不赋值为空，就会造成无限循环
                root = null;
            } else {
                queue.add(root);
            }
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
}
