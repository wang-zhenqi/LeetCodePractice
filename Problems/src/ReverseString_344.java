/**
 * Write a function that reverses a string. The input string is given as an array
 * of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseString_344 {
    public static void main(String[] args) {
        char[][] tests = {
            {'h', 'e', 'l', 'l', 'o'},
            {'w', 'o', 'r', 'l', 'd'}
        };
        ReverseString_344 obj = new ReverseString_344();
        for(char[] test : tests) {
            obj.reverseString(test);
            for(char ch : test) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }
    public void reverseString(char[] s) {
        if(s.length <= 1) {
            return;
        }
        int a = 0, b = s.length - 1;
        while(a < b) {
            char t = s[a];
            s[a] = s[b];
            s[b] = t;
            a++;
            b--;
        }
    }
}
