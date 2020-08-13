import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Say you have an array for which the i-th element is the price of a given stock
 * on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k
 * transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BestTimeToBuyAndSellStockIV_188 {
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
            int k = Integer.parseInt(line);
            line = in.readLine();
            int[] prices = stringToIntegerArray(line);

            int ret = new BestTimeToBuyAndSellStockIV_188().maxProfit(k, prices);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int maxProfit(int k, int[] prices) {
        /*
         * DP. profit[i][j][0|1] represents the maximum profit on day i,
         * after j transactions (buy and sell makes one transaction), and
         * holding 0 or 1 stock.
         *
         * profit[i][j][0] = max(profit[i-1][j][0], profit[i-1][j-1][1] + prices[i])
         * profit[i][j][1] = max(profit[i-1][j][1], profit[i-1][j][0] - prices[i])
         *
         * Initialization:
         * profit[i][0][0] = 0. On day i, there's no transaction finished and no
         * stock held, meaning that it neither bought or sold anything, so the
         * profit is 0.
         *
         * profit[0][j][1] = -prices[0]. On the first day, there are already j
         * transactions finished, after each one, the profit is always 0. And at
         * last it bought one stock again, so the profit is -prices[0].
         *
         * others = -inf, meaning that the profit is unknown yet.
         */
        int len = prices.length;
        if(len <= 1) {
            return 0;
        }

        // If k is too large, the problem can be treated as a greedy problem.
        // It will be the same as problem 122 - best time to buy and sell stock II.
        if(k > len / 2) {
            int max = 0;
            for(int i = 0; i < len - 1; i++) {
                if(prices[i] < prices[i + 1]) {
                    max += prices[i + 1] - prices[i];
                }
            }
            return max;
        }

        /*
         * State compression. Each state only relies on the its previous state,
         * so the state array can be shrunk to 2D.
         */
        int[][] profit_i = new int[k + 1][2];
        for(int j = 0; j <= k; j++) {
            profit_i[j][0] = 0;
            profit_i[j][1] = Integer.MIN_VALUE;
        }
        profit_i[0][1] = -prices[0];

        int tmp, max = Integer.MIN_VALUE;
        for(int price : prices) {
            for(int j = 0; j <= k; j++) {
                tmp = profit_i[j][0];
                profit_i[j][0] = Math.max(profit_i[j][0],
                        j == 0 ? Integer.MIN_VALUE : profit_i[j - 1][1] + price);
                profit_i[j][1] = Math.max(profit_i[j][1],
                        tmp - price);
            }
        }
        for(int j = 0; j <= k; j++) {
            max = Math.max(max, profit_i[j][0]);
        }
        return max;
    }
}