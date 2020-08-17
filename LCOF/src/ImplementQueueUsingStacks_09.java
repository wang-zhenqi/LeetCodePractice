import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ImplementQueueUsingStacks_09 {
    private final Stack<Integer> inStack;
    private final Stack<Integer> outStack;
    private int length;

    public ImplementQueueUsingStacks_09() {
        inStack = new Stack<>();
        outStack = new Stack<>();
        length = 0;
    }

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */
    public static void main(String[] args) {
        ImplementQueueUsingStacks_09 myQueue = new ImplementQueueUsingStacks_09();
        //myQueue.printQueue();

        myQueue.appendTail(3);
        //myQueue.printQueue();

        System.out.println(myQueue.deleteHead());
        //myQueue.printQueue();

        System.out.println(myQueue.deleteHead());
        //myQueue.printQueue();

        System.out.println(myQueue.deleteHead());
        //myQueue.printQueue();
    }

    public void appendTail(int value) {
        inStack.push(value);
        length++;
    }

    public int deleteHead() {
        if(length == 0) {
            return -1;
        }

        if(outStack.empty()) {
            while(!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }

        int result = outStack.pop();
        length--;
        return result;
    }

    public int peekHead() {
        if(length == 0) {
            return -1;
        }

        if(outStack.empty()) {
            while(!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }

        return outStack.peek();
    }

    public void printQueue() {
        System.out.println("Length: " + length);
        System.out.println("Head: " + peekHead());
    }
}