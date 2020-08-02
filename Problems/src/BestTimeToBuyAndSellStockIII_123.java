import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of
 * a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete
 * at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class BestTimeToBuyAndSellStockIII_123 {
    public int maxProfit(int[] prices) {
        /*
         * Version 2, DP, O(N)
         * dp[i][k][s] represents the status of transaction k on day i.
         * status: 0 - no stock held, 1 - stock held.
         * k: in the kth transaction, k = {0, 1, 2}. 0: neither bought nor sold;
         *     1: bought or sold once; 2: bought or sold twice.
         *     if (k, s) = (1, 1), it means that it bought the stock for once, and still
         *         in hold of that stock.
         *     if (k, s) = (2, 0), it means that it had been bought - sold - bought - sold
         *         the stock, and now no stock is held.
         * original values:
         *     dp[i][0][0] = 0, dp[i][0][1] = -inf (means this is impossible)
         *     dp[0][1][1] = -prices[0]
         *     dp[0][1][0] = dp[0][2][0] = dp[0][2][1] = -inf
         * formula:
         *     dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
         *     dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
         *     dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
         *     dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
         * return:
         *     (dp[n-1][2][0] != -inf) ? dp[n-1][2][0] :
         *                               ((dp[n-1][1][0] != -inf) ? dp[n-1][1][0] : 0)
         */
        int n = prices.length, invalid = Integer.MIN_VALUE;

        if(n < 2) {
            return 0;
        }

        int[][][] dp = new int[prices.length][3][2];
        for(int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = invalid;
            dp[i][1][0] = invalid;
            dp[i][1][1] = invalid;
            dp[i][2][0] = invalid;
            dp[i][2][1] = invalid;
        }
        dp[0][1][1] = -prices[0];

        for(int i = 1; i < n; i++) {
            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);

            if(dp[i - 1][1][1] != invalid) {
                dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            }

            if(dp[i - 1][1][0] != invalid) {
                dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
            }

            if(dp[i - 1][2][1] != invalid) {
                dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            }
        }

        if(dp[n - 1][2][0] < 0 && dp[n - 1][1][0] < 0) {
            return 0;
        }
        return Math.max(dp[n - 1][2][0], dp[n - 1][1][0]);
    }

    private static int[] stringToIntegerArray(String input) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] prices = stringToIntegerArray(line);

            int ret = new BestTimeToBuyAndSellStockIII_123().maxProfit(prices);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
