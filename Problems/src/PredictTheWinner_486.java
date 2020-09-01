import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of
 * the numbers from either end of the array followed by the player 2 and then
 * player 1 and so on. Each time a player picks a number, that number will not be
 * available for the next player. This continues until all the scores have been
 * chosen. The player with the maximum score wins.
 *
 * Given an array of scores, predict whether player 1 is the winner. You can
 * assume each player plays to maximize his score.
 *
 * Constraints:
 *
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed
 * 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/predict-the-winner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PredictTheWinner_486 {
    public static int[] stringToIntegerArray(String input) {
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            boolean ret = new PredictTheWinner_486().PredictTheWinner(nums);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean PredictTheWinner(int[] nums) {
        return pick(nums, 0, nums.length - 1, 0) >= 0;
    }

    private int pick(int[] nums, int leftBound, int rightBound, int player) {
        if(leftBound == rightBound) {
            return player == 0 ? nums[leftBound] : -nums[leftBound];
        }

        int score1, score2;
        if(player == 0) {
            score1 = nums[leftBound] + pick(nums, leftBound + 1, rightBound,1);
            score2 = nums[rightBound] + pick(nums, leftBound, rightBound - 1, 1);
            return Math.max(score1, score2);
        } else {
            score1 = -nums[leftBound] + pick(nums, leftBound + 1, rightBound, 0);
            score2 = -nums[rightBound] + pick(nums, leftBound, rightBound - 1, 0);
            return -Math.max(-score1, -score2);
        }
    }
}
