import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、
 * "3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StringThatRepresentsNumber_20 {
    private final int[][] STATES_TRANS = {
            {1, 2, 8, -1, 0, -1},
            {1, -1, 3, 4, 9, -1},
            {1, -1, 8, -1, -1, -1},
            {5, -1, -1, 4, 9, -1},
            {7, 6, -1, -1, -1, -1},
            {5, -1, -1, 4, 9, -1},
            {7, -1, -1, -1, -1, -1},
            {7, -1, -1, -1, 9, -1},
            {5, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, 9, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringThatRepresentsNumber_20 judge = new StringThatRepresentsNumber_20();
        while((s = in.readLine()) != null) {
            System.out.println(judge.isNumber(s));
        }
    }

    public boolean isNumber(String s) {
        //FSM.
        if(s.length() == 0) {
            return false;
        }

        int curState = 0;
        for(int i = 0; i < s.length(); i++) {
            int symbol;
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                symbol = 0;
            } else if(s.charAt(i) == '+' || s.charAt(i) == '-') {
                symbol = 1;
            } else if(s.charAt(i) == '.') {
                symbol = 2;
            } else if(s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                symbol = 3;
            } else if(s.charAt(i) == ' ') {
                symbol = 4;
            } else {
                symbol = 5;
            }
            curState = STATES_TRANS[curState][symbol];
            if(curState == -1) {
                return false;
            }
        }
        return (curState & 1) == 1;
    }
}
