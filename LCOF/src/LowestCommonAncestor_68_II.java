import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足
 * x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LowestCommonAncestor_68_II {
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

        StringBuilder output = new StringBuilder();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
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
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int p = Integer.parseInt(line);
            line = in.readLine();
            int q = Integer.parseInt(line);

            TreeNode ret = new LowestCommonAncestor_68_II()
                    .lowestCommonAncestor(root, new TreeNode(p), new TreeNode(q));

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }

    /*
     * Version 1, iteration.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || (root.left == null && root.right == null)) {
            return null;
        }

        int pVal = p.val, qVal = q.val;
        boolean pFound = false, qFound = false;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> possible = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curNode = stack.peek();
            if(!pFound && !qFound && !visited.contains(curNode)) {
                possible.push(curNode);
            }

            if(curNode.val == pVal) {
                pFound = true;
            }
            if(curNode.val == qVal) {
                qFound = true;
            }
            visited.add(curNode);
            if(pFound && qFound) {
                return possible.pop();
            }

            if(curNode.left != null && !visited.contains(curNode.left)) {
                stack.push(curNode.left);
            } else if(curNode.right != null && !visited.contains(curNode.right)) {
                stack.push(curNode.right);
            } else {
                stack.pop();
                if(curNode == possible.peek()) {
                    possible.pop();
                }
            }
        }
        return null;
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
