/*
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

/*
 * Version 2
 * Use preorder sequence to determine the root node of the whole tree and
 * its subtrees.
 * Use inorder sequence to determine the left child and right child.
 * Rebuild the tree recursively.
 */
class RebuildBinaryTree_07 {
    private int locatedIndex;
    private int[] originalPreorder;
    HashMap<Integer, Integer> inorderIndex;

    private TreeNode recursivelyBuildTree(int inorderStartIndex, int inorderEndIndex) {
        if(inorderStartIndex > inorderEndIndex) {
            return null;
        }

        TreeNode root = null;
        Integer pivotIndex = inorderIndex.get(originalPreorder[locatedIndex + 1]);
        if(pivotIndex != null) {
            root = new TreeNode(originalPreorder[++locatedIndex]);
        }

        if(root == null) {
            return null;
        }

        root.left = recursivelyBuildTree(inorderStartIndex, pivotIndex - 1);
        root.right = recursivelyBuildTree(pivotIndex + 1, inorderEndIndex);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        inorderIndex = new HashMap<>();
        locatedIndex = -1;
        originalPreorder = preorder.clone();
        for(int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        return recursivelyBuildTree(0, inorder.length - 1);
    }
}

class TestClass_07 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
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

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] preorder = stringToIntegerArray(line);
            line = in.readLine();
            int[] inorder = stringToIntegerArray(line);

            TreeNode ret = new RebuildBinaryTree_07().buildTree(preorder, inorder);

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }
}