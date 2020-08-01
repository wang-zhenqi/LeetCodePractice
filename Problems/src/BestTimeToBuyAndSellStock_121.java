import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 *
 * If you were only permitted to complete at most one transaction
 * (i.e., buy one and sell one share of the stock), design an algorithm to
 * find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class BestTimeToBuyAndSellStock_121 {
    public int maxProfit(int[] prices) {
        /*
         * Version 1, brutal force, O(N^2)
         * foreach inPrice in prices:
         *     foreach outPrice after inPrice:
         *         maxProfit = max(MAX, outPrice - inPrice)
         * return maxProfit
         */
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                if(prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
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

            int ret = new BestTimeToBuyAndSellStock_121().maxProfit(prices);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
