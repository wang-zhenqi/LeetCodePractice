import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和
 * pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumOfAQueue_59_II {
    //Version 1, use a heap to maintain the largest number. But it's a bit slow.
    //There is an O(1) method, see https://github.com/wang-zhenqi/LeetCodePractice/blob/Java/Problems/src/SlidingWindowMaximum_239.java

    private final Queue<Integer> queue;
    private final PriorityQueue<Integer> heap;

    public MaximumOfAQueue_59_II() {
        queue = new ArrayDeque<>();
        heap = new PriorityQueue<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            if(line.length() < 2) {
                System.out.println("Empty instructions");
                continue;
            }
            line = line.substring(1, line.length() - 1);
            String[] instructions = line.split(",");

            line = in.readLine();
            if(line == null || line.length() < 2) {
                System.out.println("Empty parameters");
                continue;
            }
            line = line.substring(1, line.length() - 1);
            String[] parameters = line.split(",");
            if(parameters.length != instructions.length) {
                System.out.println("parameters not match instructions");
                continue;
            }

            int cnt = 0;
            MaximumOfAQueue_59_II myQueue = null;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(String instruction : instructions) {
                if(instruction == null || instruction.isEmpty()) {
                    continue;
                }
                switch(instruction) {
                    case "\"MaxQueue\"":
                        myQueue = new MaximumOfAQueue_59_II();
                        sb.append("null,");
                        cnt++;
                        break;
                    case "\"push_back\"":
                        int para = Integer.parseInt(parameters[cnt].substring(1, parameters[cnt].length() - 1));
                        if(myQueue != null) {
                            myQueue.push_back(para);
                            sb.append("null,");
                        } else {
                            System.out.println("Not constructed yet");
                        }
                        cnt++;
                        break;
                    case "\"max_value\"":
                        if(myQueue != null) {
                            int max = myQueue.max_value();
                            sb.append(max).append(',');
                        } else {
                            System.out.println("Not constructed yet");
                        }
                        cnt++;
                        break;
                    case "\"pop_front\"":
                        if(myQueue != null) {
                            int front = myQueue.pop_front();
                            sb.append(front).append(',');
                        } else {
                            System.out.println("Not constructed yet");
                        }
                        cnt++;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + instruction);
                }
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');
            System.out.println(sb.toString());
        }
    }

    public int max_value() {
        if(!heap.isEmpty()) {
            return -heap.peek();
        }
        return -1;
    }

    public void push_back(int value) {
        queue.add(value);
        heap.add(-value);
    }

    public int pop_front() {
        if(!queue.isEmpty() && !heap.isEmpty()) {
            heap.remove(-queue.peek());
            return queue.remove();
        }
        return -1;
    }
}
