import java.io.*;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、
 * "3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StringThatRepresentsNumber_20 {
    private final int NUMBER_OF_STATES = 10;
    private final int NUMBER_OF_SYMBOLS = 6;
    private final int[][] STATES_TRANS = new int[NUMBER_OF_STATES][NUMBER_OF_SYMBOLS];

    StringThatRepresentsNumber_20() {
        for(int i = 0; i < NUMBER_OF_STATES; i++) {
            for(int j = 0; j < NUMBER_OF_SYMBOLS; j++) {
                STATES_TRANS[i][j] = -1;
            }
        }
        STATES_TRANS[0][0] = 1;
        STATES_TRANS[0][1] = 2;
        STATES_TRANS[0][2] = 8;
        STATES_TRANS[1][0] = 1;
        STATES_TRANS[1][2] = 3;
        STATES_TRANS[1][3] = 4;
        STATES_TRANS[2][0] = 1;
        STATES_TRANS[2][2] = 8;
        STATES_TRANS[3][0] = 5;
        STATES_TRANS[3][3] = 4;
        STATES_TRANS[4][1] = 6;
        STATES_TRANS[4][0] = 7;
        STATES_TRANS[5][0] = 5;
        STATES_TRANS[5][3] = 4;
        STATES_TRANS[6][0] = 7;
        STATES_TRANS[7][0] = 7;
        STATES_TRANS[7][4] = 9;
        STATES_TRANS[8][0] = 5;
        STATES_TRANS[9][4] = 9;
    }

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
