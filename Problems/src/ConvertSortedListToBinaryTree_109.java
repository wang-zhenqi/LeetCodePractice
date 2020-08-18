import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the head of a singly linked list where elements are sorted in ascending
 * order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in
 * which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConvertSortedListToBinaryTree_109 {
    private static int[] stringToIntegerArray(String input) {
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

    private static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    private static String treeNodeToString(TreeNode root) {
        if(root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if(node == null) {
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
        while((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            TreeNode ret = new ConvertSortedListToBinaryTree_109().sortedListToBST(head);

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        /*
         * Binary-search-like method.
         * Record each node in the linked list with an array, so that it will be
         * fast to locate a certain node.
         * BTW, hashmap can also record the nodes, but it seems like a bit slower
         * than array.
         */
        ListNode[] nodes = new ListNode[20000];
        int len;
        for(len = 0; head != null; head = head.next, len++) {
            nodes[len] = head;
        }

        return buildTreeDfs(0, len - 1, nodes);
    }

    private TreeNode buildTreeDfs(int begin, int end, ListNode[] nodes) {
        if(begin > end) {
            return null;
        }
        if(begin == end) {
            return new TreeNode(nodes[begin].val);
        }
        int mid = begin + (end - begin) / 2;
        TreeNode root = new TreeNode(nodes[mid].val);
        root.left = buildTreeDfs(begin, mid - 1, nodes);
        root.right = buildTreeDfs(mid + 1, end, nodes);
        return root;
    }

    //Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //Definition for a binary tree node.
    private static class TreeNode {
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
