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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

class RebuildBinaryTree_07 {
    TreeNode target = null;
    TreeNode arrow = null;
    int compIndex = 0;
    int[] oriInorder;
    int compStage;

    /*
     * Version 1
     * Build a naive tree according to the preorder result,
     * leading up to a tree that every node only has a left child.
     * And then traverse the inorder result, adjust the nodes to their
     * appropriate positions.
     * It's too slow, the time complexity might be close to O(n^2).
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if(length == 0) {
            return null;
        }
        oriInorder = inorder;

        TreeNode root = null;
        TreeNode current = root;
        //Construct a simple tree that suit the preorder.
        for(int i = 0; i < length; i++) {
            TreeNode tmp = new TreeNode(preorder[i]);
            if(root == null) {
                root = tmp;
                current = root;
            }
            else {
                current.left = tmp;
                current = current.left;
            }
        }

        //Traverse the tree in inorder, and compare the result with the given one.
        while(true) {
            inorderTraverse(root);

            // After traversal, the arrow is going to be moved as the target's
            // right child.
            if(arrow == null) { // It means the tree is finished rebuilding.
                break;
            }
            else {
                target.right = arrow;
            }
        }
        return root;
    }

    private void inorderTraverse(TreeNode root) {
        compIndex = 0;
        compStage = 0;
        arrow = null;
        target = null;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> traversed = new Stack<>();
        stack.push(root);

        // The goal of this function is to find which node needs to be moved.
        while(!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if(cur.left != null) {
                if(!traversed.contains(cur.left)) {
                    stack.push(cur.left);
                    continue;
                }
            }

            if(cur.val == oriInorder[compIndex]) {
                target = cur;
                compIndex++;
                if(target.left == arrow && arrow != null) {
                    target.left = null;
                    compStage = 1;
                }
            }
            else {
                if(compStage == 0) {
                    arrow = cur;
                }
                else {
                    break;
                }
            }
            traversed.push(stack.pop());

            if(cur.right != null) {
                if(!traversed.contains(cur.right)) {
                    stack.push(cur.right);
                }
            }
        }
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