import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Say you have an array prices for which the ith element is the price of
 * a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (i.e., buy one and sell one share of the stock
 * multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BestTimeToBuyAndSellStockII_122 {
    public int maxProfit(int[] prices) {
        /*
         * Version 3, DP
         * dp[i][s] represents the maximum profit by day i, and the status on that day
         * is s, where s = {0 (no stock held) | 1 (stock held)}.
         *
         * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
         *
         * return dp[n-1][0]
         */

        if(prices.length == 0) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
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

            int ret = new BestTimeToBuyAndSellStockII_122().maxProfit(prices);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
