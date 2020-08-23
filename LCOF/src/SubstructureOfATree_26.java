import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 限制：0 <= 节点个数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubstructureOfATree_26 {
    private static TreeNode stringToTreeNode(String input) {
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

    private static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode A = stringToTreeNode(line);
            line = in.readLine();
            TreeNode B = stringToTreeNode(line);

            boolean ret = new SubstructureOfATree_26().isSubStructure(A, B);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null && B == null) {
            return true;
        }
        if(A == null || B == null) {
            return false;
        }
        return traverse(A, B);
    }

    private boolean traverse(TreeNode A, TreeNode B) {
        if(A == null) {
            return false;
        }

        if(A.val == B.val) {
            if(findSubStructure(A, B)) {
                return true;
            }
        }
        return traverse(A.left, B) || traverse(A.right, B);
    }

    private boolean findSubStructure(TreeNode A, TreeNode B) {
        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        stackA.add(A);
        stackB.add(B);
        visited.add(B);
        while(!stackB.isEmpty()) {
            TreeNode tmp = stackB.peek();
            TreeNode pA = stackA.peek();

            if(tmp.left != null && !visited.contains(tmp.left)) {
                if(pA.left == null || tmp.left.val != pA.left.val) {
                    return false;
                }
                stackB.add(tmp.left);
                visited.add(tmp.left);
                stackA.add(pA.left);
            } else if(tmp.right != null && !visited.contains(tmp.right)){
                if(pA.right == null || tmp.right.val != pA.right.val) {
                    return false;
                }
                stackB.add(tmp.right);
                visited.add(tmp.right);
                stackA.add(pA.right);
            } else {
                stackA.pop();
                stackB.pop();
            }
        }
        return true;
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
