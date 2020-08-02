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
         * Version 3, simplified DP, time: O(N), space: O(1)
         */
        int n = prices.length, invalid = Integer.MIN_VALUE;

        if(n < 2) {
            return 0;
        }

        int dpi20 = 0, dpi21 = invalid, dpi10 = 0, dpi11 = invalid;

        for(int price : prices) {
            dpi20 = Math.max(dpi20, dpi21 + price);
            dpi21 = Math.max(dpi21, dpi10 - price);
            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, -price);
        }

        return dpi20;
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
