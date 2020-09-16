import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * <p>
 * Return the following binary tree:
 * <p>
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {
    private int[] pre, in;
    private int preIndex;

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if(input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String treeNodeToString(TreeNode root) {
        if(root == null) {
            return "[]";
        }

        StringBuilder output = new StringBuilder();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if(node == null) {
                output.append("null, ");
                continue;
            }

            output.append(node.val).append(", ");
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int[] preorder = stringToIntegerArray(line);
            line = in.readLine();
            int[] inorder = stringToIntegerArray(line);

            TreeNode ret = new ConstructBinaryTreeFromPreorderAndInorderTraversal_105().buildTree(preorder, inorder);
            String out = treeNodeToString(ret);
            System.out.println(out);
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length != inorder.length) {
            return null;
        }
        if(preorder.length < 1) {
            return null;
        }
        pre = preorder;
        in = inorder;
        preIndex = 0;
        return buildHelper(0, inorder.length - 1);
    }

    private TreeNode buildHelper(int childStart, int childEnd) {
        if(childStart > childEnd) {
            return null;
        }
        TreeNode root = null;
        for(int i = childStart; i <= childEnd; i++) {
            if(pre[preIndex] == in[i]) {
                root = new TreeNode(pre[preIndex]);
                preIndex++;
                root.left = buildHelper(childStart, i - 1);
                root.right = buildHelper(i + 1, childEnd);
                break;
            }
        }
        return root;
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
