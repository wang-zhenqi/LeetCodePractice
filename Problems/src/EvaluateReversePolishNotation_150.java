import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 *
 * Note:
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would
 * always evaluate to a result and there won't be any divide by zero operation.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EvaluateReversePolishNotation_150 {
    public static void main(String[] args) {
        EvaluateReversePolishNotation_150 obj = new EvaluateReversePolishNotation_150();
        String[][] notations = {
                {"2", "1", "+", "3", "*"},
                {"4", "13", "5", "/", "+"},
                {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"},
                {"4", "3", "-"},
                {"7", "-2", "/", "2", "-3", "-", "-"}
        };
        for(String[] tokens : notations) {
            int ret = obj.evalRPN(tokens);
            System.out.println(ret);
        }
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int a, b;
        for(String token : tokens) {
            if(!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                stack.push(Integer.parseInt(token));
            } else {
                a = stack.pop();
                b = stack.pop();
                switch(token) {
                    case "+":
                        stack.push(b + a);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "*":
                        stack.push(b * a);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
