import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中
 * 节点指针的指向。
 * <p>
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，
 * 第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * <p>
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要
 * 指向后继。还需要返回链表中的第一个节点的指针。
 * <p>
 * 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * 注意：此题对比原题有改动。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BSTAndBidirLinkedList_36 {
    /*
     * This problem is suitable to practice the operations of binary tree.
     * It doesn't offer the frame codes, so that I have to write the string parsing
     * function and main function.
     * It contains the in-order traversal, the level-order traversal, the array
     * representation of a tree, judging whether a linked list contains a circle by
     * two pointers.
     */
    private Node head, pre, tail;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        BSTAndBidirLinkedList_36 obj = new BSTAndBidirLinkedList_36();
        while((line = br.readLine()) != null) {
            Node root = stringToBinaryTree(line);
            System.out.println(obj.treeToDoublyList(root).toString());
        }
    }

    private static Node stringToBinaryTree(String sequence) {
        sequence = sequence.trim();
        int len = sequence.length();
        if(len <= 2 ||
                sequence.charAt(0) != '[' ||
                sequence.charAt(len - 1) != ']') {
            return null;
        }

        sequence = sequence.substring(1, len - 1);
        String[] nodeStrings = sequence.split(",");
        if(nodeStrings.length == 0) {
            return null;
        }
        Node root;
        if(nodeStrings[0].equals("null")) {
            return null;
        } else {
            root = new Node(Integer.parseInt(nodeStrings[0]));
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        for(int i = 1; i < nodeStrings.length; i++) {
            if(queue.isEmpty()) {
                break;
            }
            Node curNode = queue.poll();
            if(i * 2 - 1 < nodeStrings.length) {
                if(nodeStrings[i * 2 - 1].equals("null")) {
                    curNode.left = null;
                } else {
                    curNode.left = new Node(Integer.parseInt(nodeStrings[i * 2 - 1]));
                    queue.offer(curNode.left);
                }
            }
            if(i * 2 < nodeStrings.length) {
                if(nodeStrings[i * 2].equals("null")) {
                    curNode.right = null;
                } else {
                    curNode.right = new Node(Integer.parseInt(nodeStrings[i * 2]));
                    queue.offer(curNode.right);
                }
            }
        }
        return root;
    }

    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }
        head = null;
        pre = null;
        tail = null;
        inorderTraverse(root);
        head.left = tail;
        tail.right = head;
        return head;
    }

    private void inorderTraverse(Node root) {
        if(root == null) {
            return;
        }

        inorderTraverse(root.left);
        if(pre == null) {
            head = root;
        } else {
            pre.right = root;
        }

        root.left = pre;
        pre = root;
        tail = root;

        inorderTraverse(root.right);
    }

    // Definition for a Node.
    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int _val) {
            val = _val;
        }


        @Override
        public String toString() {
            Node slow = this;
            Node fast = this;
            int flag = 0, len = 0;
            while(slow.right != null && fast.right != null && fast.right.right != null) {
                len++;
                slow = slow.right;
                fast = fast.right.right;
                if(slow == fast) {
                    flag = 1;
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append('[');
            if(flag == 0) {
                Queue<Node> queue = new LinkedList<>();
                queue.offer(this);
                while(!queue.isEmpty()) {
                    Node curNode = queue.poll();
                    if(curNode.val == Integer.MIN_VALUE) {
                        sb.append("null,");
                    } else {
                        sb.append(curNode.val).append(",");
                        queue.offer(Objects.requireNonNullElseGet(curNode.left, () -> new Node(Integer.MIN_VALUE)));
                        queue.offer(Objects.requireNonNullElseGet(curNode.right, () -> new Node((Integer.MIN_VALUE))));
                    }
                }
            } else {
                Node curNode = this;
                for(int i = 0; i < len; i++) {
                    sb.append(curNode.val).append(",");
                    curNode = curNode.right;
                }
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');
            return sb.toString();
        }
    }
}
