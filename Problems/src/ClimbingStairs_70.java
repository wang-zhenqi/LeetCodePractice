import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways
 * can you climb to the top?
 * <p>
 * Constraints:
 * 1 <= n <= 45
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbingStairs_70 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new ClimbingStairs_70().climbStairs(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int climbStairs(int n) {
        /*
         * DP, it's actually a fibonacci sequence.
         * Define that ways[n] means to get to the nth level, the number of distinct
         * ways it needs.
         * It's not hard to find that, ways[n] = ways[n - 1] + ways[n - 2]
         * Also, the space can be optimized by using only two extra variables.
         */
        if(n <= 2) {
            return n;
        }

        int[] ways = new int[2];
        ways[0] = 1;
        ways[1] = 1;

        for(int i = 2; i <= n; i++) {
            ways[i & 1] = ways[0] + ways[1];
        }
        return ways[n & 1];
    }
}
