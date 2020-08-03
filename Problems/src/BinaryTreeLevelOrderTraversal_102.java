import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeLevelOrderTraversal_102 {
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

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += number + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    private static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    private static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for(List<Integer> list : nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<List<Integer>> ret = new BinaryTreeLevelOrderTraversal_102().levelOrder(root);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        /*
         * Version 2, DFS, iteratively.
         */
        List<List<Integer>> result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int level = 0;

        HashSet<TreeNode> pastNode = new HashSet<>();

        while(!stack.empty()) {
            TreeNode curNode = stack.peek();
            if(!pastNode.contains(curNode)) {
                if(result.size() - 1 >= level) {
                    result.get(level).add(curNode.val);
                } else {
                    List<Integer> entry = new ArrayList<>();
                    entry.add(curNode.val);
                    result.add(entry);
                }
                pastNode.add(curNode);
            }

            if(curNode.left != null && !pastNode.contains(curNode.left)) {
                stack.push(curNode.left);
                level++;
            } else if(curNode.right != null && !pastNode.contains(curNode.right)) {
                stack.push(curNode.right);
                level++;
            } else {
                stack.pop();
                level--;
            }
        }

        return result;
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
