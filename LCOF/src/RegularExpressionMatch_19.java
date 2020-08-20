import java.util.ArrayList;
import java.util.List;

/**
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符
 * 可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和
 * "ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * <p>
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母以及字符.和*，无连续的 '*'。
 * 注意：本题与主站 10题相同：https://leetcode-cn.com/problems/regular-expression-matching/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RegularExpressionMatch_19 {
    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatch_19()
                .isMatch("aaa", "a*a"));
    }

    public boolean isMatch(String s, String p) {
        FSM fsm = new FSM();
        fsm.parsePattern(p);
        return fsm.matchPattern(s);
    }

    private static class FSM {
        List<States> states;

        FSM() {
            states = new ArrayList<>();
        }

        void parsePattern(String p) {
            int length = p.length();
            int curStateNum = 0;
            int maxStateNum = 0;
            List<Integer> stateBeforeStar = new ArrayList<>();
            for(int i = 0; i < length; i++) {
                int nextState = ++maxStateNum;
                states.add(new States(curStateNum, p.charAt(i), nextState));
                for(Integer stateBefore : stateBeforeStar) {
                    states.add(new States(stateBefore, p.charAt(i), nextState));
                }
                if(i + 1 < length && p.charAt(i + 1) == '*') {
                    stateBeforeStar.add(curStateNum);
                    states.add(new States(nextState, p.charAt(i), nextState));
                    i++;
                } else {
                    stateBeforeStar.clear();
                }
                curStateNum = nextState;
            }
            states.add(new States(curStateNum, null, -1));
            if(!stateBeforeStar.isEmpty()) {
                for(Integer stateBefore : stateBeforeStar) {
                    states.add(new States(stateBefore, null, -1));
                }
            }
        }

        List<Integer> getNextState(int start, Character trig) {
            List<Integer> possibleStates = new ArrayList<>();
            for(States state : states) {
                if(state.startState == start && (state.trigger == trig ||
                        (state.trigger != null && state.trigger == '.' && trig != null))) {
                    possibleStates.add(state.endState);
                }
            }
            if(possibleStates.isEmpty()) {
                possibleStates.add(-2);
            }
            return possibleStates;
        }

        private boolean validateState(int start, String s, int index) {
            List<Integer> candidates = getNextState(start, index == s.length() ? null : s.charAt(index));
            for(int state : candidates) {
                if(state == -1) {
                    return true;
                }
                if(state == -2) {
                    return false;
                }
                if(validateState(state, s, index + 1)) {
                    return true;
                }
            }
            return false;
        }

        public boolean matchPattern(String s) {
            return validateState(0, s, 0);
        }

        private static class States {
            int startState;
            Character trigger;
            int endState;

            States(int start, Character trig, int end) {
                startState = start;
                trigger = trig;
                endState = end;
            }
        }
    }
}