/*
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * 限制：
 * 0 <= 链表长度 <= 10000
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ReversePrintLinkList_06 {
    public static int[] stringToIntegerArray(String input) {
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

    public static String integerArrayToString(int[] nums, int length) {
        if(length == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result.append(number).append(", ");
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            int[] ret = new ReversePrintLinkList_06().reversePrint(head);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }

    /*
     * This is quite easy, traverse the link list twice.
     * First time to get the length of the link list, then build an array
     * with this length.
     * Second time to assign every value of the node to the array from back
     * to front.
     */
    public int[] reversePrint(ListNode head) {
        int count = 0;
        for(ListNode node = head; node != null; node = node.next) {
            count++;
        }
        int[] ans = new int[count];
        int i = count - 1;
        for(ListNode node = head; node != null; node = node.next) {
            ans[i] = node.val;
            i--;
        }
        return ans;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}