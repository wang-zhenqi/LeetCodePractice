import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which
 * represents the position (0-indexed) in the linked list where tail connects to.
 * If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedListCycleII_142 {
    // version 2. Using fast and slow pointers.
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        boolean flag = false;
        while(fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                flag = true;
                break;
            }
        }
        if(flag) {
            ListNode ptr = head;
            while(ptr != slow) {
                ptr = ptr.next;
                slow = slow.next;
            }
            return slow;
        } else {
            return null;
        }
    }
}

class MainClass_142 {
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int pos = Integer.parseInt(line);

            ListNode tmp = null, p = head, tail = null;
            for(int i = 0; p != null; i++, p = p.next) {
                if(i == pos) {
                    tmp = p;
                }
                if(p.next == null) {
                    tail = p;
                }
            }
            tail.next = tmp;

            ListNode ret = new LinkedListCycleII_142().detectCycle(head);
            if(ret == null) {
                System.out.println("null");
            } else {
                System.out.println(ret.val);
            }
        }
    }
}