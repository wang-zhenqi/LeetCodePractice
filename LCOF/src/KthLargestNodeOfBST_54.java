import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * <p>
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *   3
 *  / \
 * 1   4
 *  \
 *  2
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 * 输出: 4
 * <p>
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthLargestNodeOfBST_54 {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
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

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            int ret = new KthLargestNodeOfBST_54().kthLargest(root, k);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    private int result;
    private int count;
    public int kthLargest(TreeNode root, int k) {
        count = k;
        reverseInorderTraversal(root);
        return result;
    }

    private void reverseInorderTraversal(TreeNode root) {
        if(root == null || count <= 0) {
            return;
        }

        reverseInorderTraversal(root.right);
        if(--count == 0) {
            result = root.val;
            return;
        }
        reverseInorderTraversal(root.left);
    }

    // Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
