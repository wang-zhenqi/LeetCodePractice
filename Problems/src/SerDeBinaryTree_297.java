import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a
 * binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SerDeBinaryTree_297 {
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Codec codec = new Codec();
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            String out = codec.serialize(root);
            System.out.println(out);

            root = codec.deserialize(out);
            dfs(root);
            System.out.println();
        }
    }

    private static void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.print(root.val + " ");
        dfs(root.left);
        dfs(root.right);
    }
}

class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!queue.isEmpty()) {
            int len = queue.size();
            boolean allLeaves = true;
            for(int i = 0; i < len; i++) {
                TreeNode curNode = queue.poll();
                if(curNode != null) {
                    sb.append(curNode.val).append(',');
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                    if(curNode.left != null || curNode.right != null) {
                        allLeaves = false;
                    }
                } else {
                    sb.append("null,");
                }
            }
            if(allLeaves) {
                break;
            }
        }
        if(sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() < 3) {
            return null;
        }
        data = data.substring(1, data.length() - 1);
        String[] nodes = data.split(",");
        if(nodes[0].equals("null")) {
            return null;
        }

        TreeNode root;
        Queue<TreeNode> queue = new LinkedList<>();
        root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.offer(root);
        int idx = 1;
        while(idx < nodes.length && !queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if(nodes[idx].equals("null")) {
                curNode.left = null;
            } else {
                curNode.left = new TreeNode(Integer.parseInt(nodes[idx]));
                queue.offer(curNode.left);
            }
            idx++;

            if(nodes[idx].equals("null")) {
                curNode.right = null;
            } else {
                curNode.right = new TreeNode(Integer.parseInt(nodes[idx]));
                queue.offer(curNode.right);
            }
            idx++;
        }
        return root;
    }
}

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}