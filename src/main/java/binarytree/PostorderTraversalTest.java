package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 后序遍历，leetcode第145题
 */
public class PostorderTraversalTest {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, Integer> trace = new HashMap<>();
        while (root != null || !stack.isEmpty()) {
            // 参考递归写法程序运行的思路，需要用栈来存储节点信息，
            // 由于后序，所以需要保留所有左节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 这里不能直接pop，因为区别于前序和中序，到这里还不能把中间节点弹出来
            root = stack.peek();
            // 当弹出中间节点时，还会继续再遍历右子节点，这个时候如果不用轨迹来判断是否已经遍历过，
            // 则会出现无限循环
            if (root.right != null && trace.get(root.right) == null) {
                root = root.right;
            } else {
                root = stack.pop();
                if (trace.get(root) == null) {
                    trace.put(root, 1);
                    list.add(root.val);
                }
                root = null;
            }
        }
        return list;
    }
}
