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
        String[] strings = {
                "aaa",
                "adeswwfsbcytfgbc",
                "a"
        };
        String[] patterns = {
                "a*a",
                ".*bc",
                "ab*"
        };
        RegularExpressionMatch_19 obj = new RegularExpressionMatch_19();

        for(int i = 0; i < patterns.length; i++) {
            boolean r = obj.isMatch(strings[i], patterns[i]);
            System.out.println(r);
        }
    }

    public boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        int pIdx = 0, sIdx = 0;
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();

        return scanned(sChars, pIdx, pChars, sIdx);
    }

    private boolean scanned(char[] string, int sIdx, char[] pattern, int pIdx) {
        if(sIdx == string.length && pIdx == pattern.length) {
            return true;
        }
        if(sIdx < string.length && pIdx == pattern.length) {
            return false;
        }
        if(pIdx < pattern.length - 1 && pattern[pIdx + 1] == '*') {
            if((sIdx < string.length && string[sIdx] == pattern[pIdx]) ||
                    (pattern[pIdx] == '.' && sIdx < string.length)) {
                return scanned(string, sIdx + 1, pattern, pIdx) ||
                        scanned(string, sIdx + 1, pattern, pIdx + 2) ||
                        scanned(string, sIdx, pattern, pIdx + 2);
            } else {
                return scanned(string, sIdx, pattern, pIdx + 2);
            }
        }
        if((sIdx < string.length && string[sIdx] == pattern[pIdx]) ||
                (pattern[pIdx] == '.' && sIdx < string.length)) {
            return scanned(string, sIdx + 1, pattern, pIdx + 1);
        }
        return false;
    }
}