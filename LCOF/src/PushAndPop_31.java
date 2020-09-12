import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均
 * 不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
 * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 * 提示：
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 *
 * 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PushAndPop_31 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] pushed = stringToIntegerArray(line);
            line = in.readLine();
            int[] popped = stringToIntegerArray(line);

            boolean ret = new PushAndPop_31().validateStackSequences(pushed, popped);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int length = pushed.length;
        if(length == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for(int push : pushed) {
            stack.push(push);
            while(!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}
