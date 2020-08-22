import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 * <p>
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstCommonNodeOfTwoLinkedList_52 {
    private static String listNodeToString(ListNode node) {
        if(node == null) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        while(node != null) {
            result.append(node.val).append(", ");
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        String lineA = in.readLine();
        String lineB = in.readLine();
        line = in.readLine();
        int skipA = Integer.parseInt(line);
        line = in.readLine();
        int skipB = Integer.parseInt(line);

        lineA = lineA.trim().substring(1, lineA.length() - 1);
        lineB = lineB.trim().substring(1, lineB.length() - 1);

        String[] elementsInA = lineA.split(",");
        String[] elementsInB = lineB.split(",");

        ListNode listA = null;
        ListNode listB = null;
        ListNode tmp = null;
        ListNode intersect = null;
        for(String element : elementsInA) {
            if(listA == null) {
                listA = new ListNode(Integer.parseInt(element));
                tmp = listA;
            } else {
                tmp.next = new ListNode(Integer.parseInt(element));
                tmp = tmp.next;
            }
            if(skipA-- == 0) {
                intersect = tmp;
            }
        }

        tmp = null;
        for(String element : elementsInB) {
            if(skipB > 0) {
                if(listB == null) {
                    listB = new ListNode(Integer.parseInt(element));
                    tmp = listB;
                } else {
                    tmp.next = new ListNode(Integer.parseInt(element));
                    tmp = tmp.next;
                }
                skipB--;
            }
            if(skipB == 0) {
                assert tmp != null;
                tmp.next = intersect;
                break;
            }
        }

        ListNode ret = new FirstCommonNodeOfTwoLinkedList_52().getIntersectionNode(listA, listB);

        String out = listNodeToString(ret);

        System.out.print(out);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        if(p1 == null || p2 == null) {
            return null;
        }
        int cnt = 0;
        while(cnt < 2) {
            if(p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            } else {
                return p1;
            }

            if(p1 == null) {
                p1 = headB;
                cnt++;
            }
            if(p2 == null) {
                p2 = headA;
            }
        }
        return null;
    }

    //Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
