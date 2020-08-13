import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up
 * that amount. If that amount of money cannot be made up by any combination of the
 * coins, return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CoinChange_322 {
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
            int[] coins = stringToIntegerArray(line);
            line = in.readLine();
            int amount = Integer.parseInt(line);

            int ret = new CoinChange_322().coinChange(coins, amount);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int coinChange(int[] coins, int amount) {
        /*
         * DP, numNeed[i] represents the minimum amount of coins that consist of
         * the amount i.
         * numNeed[i] = min(numNeed[i - coin]) + 1 where coin belongs to coins.
         */
        int[] numNeed = new int[amount + 1];
        for(int i = 1; i <= amount; i++) {
            numNeed[i] = amount + 1;
            for(int coin : coins) {
                if(i >= coin) {
                    numNeed[i] = Math.min(numNeed[i], numNeed[i - coin] + 1);
                }
            }
        }
        return numNeed[amount] == amount + 1 ? -1 : numNeed[amount];
    }
}
