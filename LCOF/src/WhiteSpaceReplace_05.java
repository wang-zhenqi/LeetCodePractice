/*
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"
 * 示例：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 * 限制：
 * 0 <= s 的长度 <= 10000
 */

class WhiteSpaceReplace_05 {
    public String replaceSpace(String s) {
//        s.replace(" ", "%20");
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                result.append("%20");
            }
            else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
}

class TestClass_05 {
    public static void main(String[] args) {
        String input = "We are the champions";

        String ret = new WhiteSpaceReplace_05().replaceSpace(input);
        System.out.print(ret);
    }
}