import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop
 * 的时间复杂度都是 O(1)。
 * <p>
 * 提示：
 * 各函数的调用总次数不超过 20000 次
 * <p>
 * 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinFunctionStack_30 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Queue<Integer> instructions = new LinkedList<>();
        Queue<Integer> operationNums = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        while((line = in.readLine()) != null) {
            instructions.clear();
            operationNums.clear();
            parseInstruction(line, instructions);
            line = in.readLine();
            parseOperation(line, operationNums);
            if(instructions.size() != operationNums.size()) {
                System.out.println("Wrong input");
                continue;
            }
            MinStack obj = null;
            while(!instructions.isEmpty() && !operationNums.isEmpty()) {
                int ins = instructions.poll();
                Integer op = operationNums.poll();
                switch(ins) {
                    case 0:
                        obj = new MinStack();
                        break;
                    case 1:
                        if(obj != null && op != null) {
                            obj.push(op);
                        }
                        break;
                    case 2:
                        if(obj != null) {
                            result.add(obj.min());
                        }
                        break;
                    case 3:
                        if(obj != null) {
                            result.add(obj.top());
                        }
                        break;
                    case 4:
                        if(obj != null) {
                            obj.pop();
                        }
                        break;
                    default:
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int n : result) {
                sb.append(n).append(",");
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            System.out.println(sb.toString());
        }
    }

    private static String formatInput(String sequence) {
        sequence = sequence.trim();
        if(sequence.length() <= 2) {
            return null;
        }
        if(sequence.charAt(0) == '[' && sequence.charAt(sequence.length() - 1) == ']') {
            sequence = sequence.substring(1, sequence.length() - 1);
        }
        return sequence;
    }

    private static void parseOperation(String sequence, Queue<Integer> operationNums) {
        sequence = formatInput(sequence);
        if(sequence == null) {
            return;
        }

        String[] nums = sequence.split(",");
        for(String num : nums) {
            if(num.length() <= 2) {
                operationNums.add(null);
                continue;
            }
            num = num.substring(1, num.length() - 1);
            operationNums.add(Integer.parseInt(num));
        }
    }

    private static void parseInstruction(String sequence, Queue<Integer> instructions) {
        sequence = formatInput(sequence);
        if(sequence == null) {
            return;
        }

        String[] ins = sequence.split(",");
        for(String i : ins) {
            switch(i) {
                case "\"MinStack\"":
                    instructions.add(0);
                    break;
                case "\"push\"":
                    instructions.add(1);
                    break;
                case "\"min\"":
                    instructions.add(2);
                    break;
                case "\"top\"":
                    instructions.add(3);
                    break;
                case "\"pop\"":
                    instructions.add(4);
                    break;
                default:
                    break;
            }
        }
    }
}

class MinStack {
    Stack<Integer> elements;
    Stack<Integer> minimals;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        elements = new Stack<>();
        minimals = new Stack<>();
    }

    public void push(int x) {
        elements.push(x);
        if(minimals.isEmpty() || minimals.peek() >= x) {
            minimals.push(x);
        }
    }

    public void pop() {
        if(elements.isEmpty()) {
            return;
        }
        if(elements.pop().equals(minimals.peek())) {
            minimals.pop();
        }
    }

    public int top() {
        return elements.peek();
    }

    public int min() {
        return minimals.peek();
    }
}