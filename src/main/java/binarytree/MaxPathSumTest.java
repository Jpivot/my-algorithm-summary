package binarytree;

/**
 * 124. 二叉树中的最大路径和
 */
public class MaxPathSumTest {
    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode nodeNegative8 = new TreeNode(-8);
        TreeNode node9 = new TreeNode(9);
        node7.left = nodeNegative8;
        node7.right = node9;
        TreeNode nodeNegative2 = new TreeNode(-2);
        TreeNode node3 = new TreeNode(3);
        node9.left = nodeNegative2;
        node9.right = node3;
        TreeNode nodeAgain9 = new TreeNode(9);
        TreeNode node11 = new TreeNode(11);
        nodeNegative2.left = nodeAgain9;
        nodeNegative2.right = node11;
        TreeNode node21 = new TreeNode(21);
        TreeNode node51 = new TreeNode(51);
        node3.right = node21;
        node3.left = node51;

        System.out.println(new MaxPathSumTest().maxPathSum(node7));
        traverse(node7);
    }

    int maxNum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // 这里路径的意思是只能选择一条从父节点到子节点的贯穿的路径
        // 不会产生分叉
        maxGain(root);
        return maxNum;
    }

    int maxGain(TreeNode root) {
        if (root == null) return 0;
        // 用0参与计算，是因为有可能两个子节点都是负值
        int left = Math.max(0, maxGain(root.left));
        int right = Math.max(0, maxGain(root.right));
        maxNum = Math.max(maxNum, left + right + root.val);
        // 返回的是该节点的最大贡献值
        return Math.max(left, right) + root.val;
    }

    static void traverse(TreeNode root) {
        if (root != null) {
            traverse(root.left);
            System.out.print(root.val + " ");
            traverse(root.right);
        }
    }
}
