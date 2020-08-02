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
         * Version 1, Brutal force, O(N^2)
         * From beginning to the end, divide the whole prices into two at every
         * position, calculate the maximum profit of each part, and then choose the
         * maximum.
         */
        int result = 0;
        BestTimeToBuyAndSellStock_121 single = new BestTimeToBuyAndSellStock_121();
        for(int i = 0; i < prices.length; i++) {
            int[] left = Arrays.copyOfRange(prices, 0, i);
            int[] right = Arrays.copyOfRange(prices, i, prices.length);
            int leftMax = single.maxProfit(left);
            int rightMax = single.maxProfit(right);
            if(leftMax + rightMax > result) {
                result = leftMax + rightMax;
            }
        }
        return result;
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
