import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes,
 * only nodes itself may be changed.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class SwapNodesInPairs_24 {
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

    private static String listNodeToString(ListNode node) {
        if(node == null) {
            return "[]";
        }

        String result = "";
        while(node != null) {
            result += node.val + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            ListNode ret = new SwapNodesInPairs_24().swapPairs(head);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }

    public ListNode swapPairs(ListNode head) {
        /*
         * Version 2. Use only two pointers, from the beginning to the end, swap each
         * two nodes at a time.
         */
        ListNode prev = new ListNode(0);
        prev.next = head;
        ListNode result = prev;
        ListNode first, second;

        while(prev.next != null && prev.next.next != null) {
            first = prev.next;
            second = first.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;
            prev = first;
        }
        return result.next;
    }

    // Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}