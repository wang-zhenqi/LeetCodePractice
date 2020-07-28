import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer
 * k and an integer array nums, which contains initial elements from the stream.
 * For each call to the method KthLargest.add, return the element representing the
 * kth largest element in the stream.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class KthLargestElementInAStream_703 {
    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */

    private final PriorityQueue<Integer> priorityQueue;
    private final int kth;

    public KthLargestElementInAStream_703(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>();
        kth = k;
        for(int num : nums) {
            this.add(num);
        }
    }

    public int add(int val) {
        if(priorityQueue.size() < kth) {
            priorityQueue.add(val);
        } else if(val > priorityQueue.peek()){
            priorityQueue.remove();
            priorityQueue.add(val);
        }
        return priorityQueue.peek();
    }
}

class MainClass_703 {
    public static void main(String[] args) {
        int k = 2;
        int[] arr = {0};
        KthLargestElementInAStream_703 kthLargest = new KthLargestElementInAStream_703(k, arr);
        System.out.println(kthLargest.add(-1));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(-2));
        System.out.println(kthLargest.add(-4));
        System.out.println(kthLargest.add(3));
    }
}