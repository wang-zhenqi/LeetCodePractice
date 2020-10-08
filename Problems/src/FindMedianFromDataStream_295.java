import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list
 * is even, there is no middle value. So the median is the mean of the two middle
 * value.
 *
 * For example,
 * [2,3,4], the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure.
 * double findMedian() - Return the median of all elements so far.
 *
 * Follow up:
 * If all integer numbers from the stream are between 0 and 100, how would you
 * optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would
 * you optimize it?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMedianFromDataStream_295 {
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