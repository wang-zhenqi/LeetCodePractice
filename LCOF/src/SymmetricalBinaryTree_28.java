import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 限制：
 * 0 <= 节点个数 <= 1000
 * <p>
 * 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SymmetricalBinaryTree_28 {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if(input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if(index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if(!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if(index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if(!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            boolean ret = new SymmetricalBinaryTree_28().isSymmetric(root);

            String out = booleanToString(ret);

            System.out.println(out);
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || recursion(root.left, root.right);
    }

    private boolean recursion(TreeNode left, TreeNode right) {
        if(left == null && right == null) {
            return true;
        }

        if(left == null || right == null || left.val != right.val) {
            return false;
        }

        return recursion(left.left, right.right) && recursion(left.right, right.left);
    }

    //Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}