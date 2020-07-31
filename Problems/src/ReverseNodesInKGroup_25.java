import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 *
 * k is a positive integer and is less than or equal to the length
 * of the linked list. If the number of nodes is not a multiple of
 * k then left-out nodes in the end should remain as it is.
 *
 * Example:
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class ReverseNodesInKGroup_25 {
    // Similar to SwapNodesInPairs_24, using several pointers to reorder the
    // linked list.
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode[] nodeArray = new ListNode[k];
        ListNode prev = new ListNode(0);
        ListNode result = prev;
        prev.next = head;

        while(prev.next != null) {
            boolean flag = true;

            nodeArray[0] = prev.next;
            for(int i = 1; i < k; i++) {
                nodeArray[i] = nodeArray[i - 1].next;
                if(nodeArray[i] == null) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                nodeArray[0].next = nodeArray[k - 1].next;
                for(int i = 1; i < k; i++) {
                    nodeArray[i].next = nodeArray[i - 1];
                }
                prev.next = nodeArray[k - 1];
                prev = nodeArray[0];
            } else {
                break;
            }
        }
        return result.next;
    }
}

class MainClass_25 {
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

    public static ListNode stringToListNode(String input) {
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

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += node.val + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            ListNode ret = new ReverseNodesInKGroup_25().reverseKGroup(head, k);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}