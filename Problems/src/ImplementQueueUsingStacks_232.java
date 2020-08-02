import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class ImplementQueueUsingStacks_232 {
    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    private final Stack<Integer> innerStack;
    private final Stack<Integer> tmpStack;

    /**
     * Initialize your data structure here.
     */
    public ImplementQueueUsingStacks_232() {
        innerStack = new Stack<>();
        tmpStack = new Stack<>();
    }

    public static void main(String[] args) {
        ImplementQueueUsingStacks_232 myQueue = new ImplementQueueUsingStacks_232();
        myQueue.printQueue();
        myQueue.push(1);
        myQueue.printQueue();
        myQueue.push(2);
        myQueue.printQueue();
        System.out.println("Pop an element " + myQueue.pop());
        myQueue.printQueue();
        System.out.println("Top of the queue is " + myQueue.peek());
        System.out.println("The queue is " + (myQueue.empty() ? "empty." : "not empty."));
        System.out.println("Pop an element " + myQueue.pop());
        System.out.println("The queue is " + (myQueue.empty() ? "empty." : "not empty."));
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if(innerStack.empty()) {
            innerStack.push(x);
        } else {
            while(!innerStack.empty()) {
                tmpStack.push(innerStack.pop());
            }
            innerStack.push(x);
            while(!tmpStack.empty()) {
                innerStack.push(tmpStack.pop());
            }
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        return innerStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return innerStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return innerStack.empty();
    }

    public void printQueue() {
        StringBuilder sb = new StringBuilder();
        if(innerStack.empty()) {
            System.out.println("NULL");
            return;
        }
        while(!innerStack.empty()) {
            int tmp = innerStack.pop();
            sb.append(tmp).append("  ");
            tmpStack.push(tmp);
        }
        System.out.println(sb.toString());
        while(!tmpStack.empty()) {
            innerStack.push(tmpStack.pop());
        }
    }
}