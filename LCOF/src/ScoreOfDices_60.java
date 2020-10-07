import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * 限制：
 * 1 <= n <= 11
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ScoreOfDices_60 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            double[] ret = new ScoreOfDices_60().twoSum(n);

            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for(double r : ret) {
                sb.append(r).append(',');
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');
            System.out.println(sb.toString());
        }
    }

    public double[] twoSum(int n) {
        double[] probabilities = new double[5 * n + 1];
        /*
         * At beginning, the array 'probabilities' stores the number of conditions that
         * forms the certain score.
         * For example, when n == 1, for each score, there is only 1 condition;
         * when n == 2, the scores are       [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
         * and the numbers of conditions are [1, 2, 3, 4, 5, 6, 5, 4,  3,  2,  1].
         */
        for(int i = 0; i < 6; i++) {
            probabilities[i] = 1.0;
        }

        //Calculate the numbers
        for(int i = 2; i <= n; i++) {
            probabilities[5 * i] = 1.0;
            //Every round relies on the previous one, so calculate each condition
            //from back to front.
            for(int j = 5 * i - 1; j >= 1; j--) {
                for(int k = j - 1; k > j - 6 && k >= 0; k--) {
                    probabilities[j] += probabilities[k];
                }
            }
        }

        double factor = myPower(n);

        //Now the array probabilities really represents the probabilities of each
        //condition.
        for(int i = 0; i < 5 * n + 1; i++) {
            probabilities[i] /= factor;
        }

        return probabilities;
    }

    //Practice the fast power algorithm.
    private double myPower(int p) {
        double r = 1.0;
        int base = 6;
        while(p != 0) {
            if((p & 1) == 1) {
                r *= 6;
            }
            base *= base;
            p >>= 1;
        }
        return r;
    }
}
