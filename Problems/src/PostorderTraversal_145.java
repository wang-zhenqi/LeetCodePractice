import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes'
 * values.
 * <p>
 * Constraints:
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * <p>
 * Follow up:
 * Recursive solution is trivial, could you do it iteratively?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PostorderTraversal_145 {
    List<Integer> result = new ArrayList<>();

    private static TreeNode stringToTreeNode(String input) {
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

    private static String integerArrayListToString(List<Integer> nums, int length) {
        if(length == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result.append(number).append(", ");
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    private static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<Integer> ret = new PostorderTraversal_145().postorderTraversal(root);

            String out = integerArrayListToString(ret);

            System.out.println(out);
        }
    }

    /*
     * Version 2, iteration.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curNode = stack.peek();
            if(curNode.left != null && !visited.contains(curNode.left)) {
                stack.push(curNode.left);
                continue;
            }
            if(curNode.right != null && !visited.contains(curNode.right)) {
                stack.push(curNode.right);
                continue;
            }
            result.add(curNode.val);
            visited.add(curNode);
            stack.pop();
        }
        return result;
    }

    //Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
