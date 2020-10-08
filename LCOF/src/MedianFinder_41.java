import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 限制：
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 *
 * 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianFinder_41 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            if(line.length() < 3) {
                System.out.println("Wrong instruction format");
                continue;
            }
            line = line.substring(1, line.length() - 1);
            String[] instructions = line.split(",");

            line = in.readLine();
            if(line == null || line.length() < 3) {
                System.out.println("Wrong operator format");
                continue;
            }
            line = line.substring(2, line.length() - 2);
            String[] operators = line.split("],\\[");

            MedianFinder obj = null;
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for(int i = 0; i < instructions.length; i++) {
                switch(instructions[i]) {
                    case "\"MedianFinder\"":
                        assert (obj == null);
                        obj = new MedianFinder();
                        sb.append("null,");
                        break;
                    case "\"addNum\"":
                        assert (obj != null);
                        obj.addNum(Integer.parseInt(operators[i]));
                        sb.append("null,");
                        break;
                    case "\"findMedian\"":
                        assert (obj != null);
                        sb.append(obj.findMedian()).append(',');
                        break;
                    default:
                        break;
                }
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');
            System.out.println(sb.toString());
        }
    }
}

/**
 * Version 2, Priority Queue.
 * Assign a maximum heap to store the first half, and a minimum heap to store the second
 * half. To find the median is to get the top elements of these heaps.
 */
class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(maxHeap.size() == minHeap.size()) {
            if(maxHeap.isEmpty() || num < minHeap.peek()) {
                maxHeap.add(num);
            } else {
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            }
        } else {
            if(!maxHeap.isEmpty() && num > maxHeap.peek()) {
                minHeap.add(num);
            } else {
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            }
        }
    }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size() && !maxHeap.isEmpty()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else if(!maxHeap.isEmpty()) {
            return maxHeap.peek();
        } else {
            return 0.0;
        }
    }
}