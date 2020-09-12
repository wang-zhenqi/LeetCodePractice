import com.sun.source.tree.Tree;

import javax.swing.tree.TreeNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
 * 第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 提示：
 * 节点总数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintBinaryTree_32_III {
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

    private static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    private static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    private static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<List<Integer>> ret = new PrintBinaryTree_32_III().levelOrder(root);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int level = 0;
            while(!queue.isEmpty()) {
                int n = queue.size();
                Integer[] row = new Integer[n];
                for(int i = 0; i < n; i++) {
                    TreeNode curNode = queue.poll();
                    if(curNode == null) {
                        continue;
                    }
                    if(curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    if(curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                    if((level & 1) == 0) {
                        row[i] = curNode.val;
                    } else {
                        row[n - i - 1] = curNode.val;
                    }
                }
                level++;
                result.add(new ArrayList<>(Arrays.asList(row)));
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
