import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个
 * 节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000
 *
 * 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DuplicateComplexLinkedList_35 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        DuplicateComplexLinkedList_35 obj = new DuplicateComplexLinkedList_35();
        StringBuilder sb = new StringBuilder();
        HashMap<Node, Integer> map = new HashMap<>();
        while((line = in.readLine()) != null) {
            Node head = buildList(line);
            map.clear();
            Node result = obj.copyRandomList(head);
            Node p = result;
            int i = 0;
            while(p != null) {
                map.put(p, i);
                p = p.next;
                i++;
            }

            if(sb.length() > 0) {
                sb.delete(0, sb.length());
            }
            sb.append('[');
            p = result;
            while(p != null) {
                sb.append('[');
                sb.append(p.val).append(',');
                sb.append(p.random == null ? "null" : map.get(p.random));
                sb.append(']').append(',');
                p = p.next;
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');

            System.out.println(sb.toString());
        }
    }

    private static Node buildList(String sequence) {
        sequence = sequence.trim();
        if(sequence.charAt(0) != '[' || sequence.charAt(sequence.length() - 1) != ']') {
            return null;
        }
        sequence = sequence.substring(1, sequence.length() - 1);
        if(sequence.length() < 3 || sequence.charAt(0) != '[' || sequence.charAt(sequence.length() - 1) != ']') {
            return null;
        }
        sequence = sequence.substring(1, sequence.length() - 1);
        String[] nodes = sequence.split("],\\[");
        Node[] list = new Node[nodes.length];
        for(int i= 0; i < list.length; i++) {
            list[i] = new Node(0);
        }

        int i = 0;
        for(String node : nodes) {
            String[] values = node.split(",");
            list[i].val = Integer.parseInt(values[0]);
            list[i].next = i < nodes.length - 1 ? list[i + 1] : null;
            list[i].random = values[1].equals("null") ? null : list[Integer.parseInt(values[1])];
            i++;
        }
        return list[0];
    }

    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        int len = 0;
        Node p = head;
        HashMap<Node, Integer> map = new HashMap<>();
        while(p != null) {
            map.put(p, len);
            len++;
            p = p.next;
        }
        Node[] result = new Node[len];
        for(int i = 0; i < len; i++) {
            result[i] = new Node(0);
        }

        p = head;
        for(int i = 0; i < len; i++) {
            result[i].val = p.val;
            result[i].next = i < len - 1 ? result[i + 1] : null;
            result[i].random = p.random == null ? null : result[map.get(p.random)];
            p = p.next;
        }
        return result[0];
    }

    // Definition for a Node.
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
