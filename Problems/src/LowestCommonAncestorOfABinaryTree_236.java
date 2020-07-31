import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both
 * p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LowestCommonAncestorOfABinaryTree_236 {
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if(root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode leftBranch = lowestCommonAncestor(root.left, p, q);
        TreeNode rightBranch = lowestCommonAncestor(root.right, p, q);
        if(leftBranch != null && rightBranch != null) {
            return root;
        } else if(leftBranch == null) {
            return rightBranch;
        } else {
            return leftBranch;
        }
    }
}

class MainClass_236 {
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

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += node.val + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int p = Integer.parseInt(line);
            line = in.readLine();
            int q = Integer.parseInt(line);

            TreeNode ret = new LowestCommonAncestorOfABinaryTree_236().lowestCommonAncestor(root, p, q);

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }
}