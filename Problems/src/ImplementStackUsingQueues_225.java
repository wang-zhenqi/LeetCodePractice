import java.util.Deque;
import java.util.LinkedList;

/**
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ImplementStackUsingQueues_225 {
    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */

    private final Deque<Integer> innerQueue;

    /**
     * Initialize your data structure here.
     */
    public ImplementStackUsingQueues_225() {
        innerQueue = new LinkedList<>();
    }

    public static void main(String[] args) {
        ImplementStackUsingQueues_225 myStack = new ImplementStackUsingQueues_225();
        myStack.printStack();
        myStack.push(1);
        myStack.printStack();
        myStack.push(2);
        myStack.printStack();
        System.out.println("Pop an element " + myStack.pop());
        myStack.printStack();
        System.out.println("Top of the Stack is " + myStack.top());
        System.out.println("The queue is " + (myStack.empty() ? "empty." : "not empty."));
        System.out.println("Pop an element " + myStack.pop());
        System.out.println("The queue is " + (myStack.empty() ? "empty." : "not empty."));
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        innerQueue.addLast(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return innerQueue.removeLast();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return innerQueue.getLast();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return innerQueue.isEmpty();
    }

    public void printStack() {
        int len = innerQueue.size();
        if(len == 0) {
            System.out.println("NULL");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            int t = innerQueue.removeLast();
            sb.append(t).append("  ");
            innerQueue.addFirst(t);
        }
        System.out.println(sb.toString());
    }
}