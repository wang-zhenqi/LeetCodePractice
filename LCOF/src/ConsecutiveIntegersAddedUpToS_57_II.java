import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 * 限制：
 * 1 <= target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConsecutiveIntegersAddedUpToS_57_II {
    public static String int2dArrayToString(int[] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int item : array) {
            sb.append(Integer.toString(item));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int target = Integer.parseInt(line);

            int[][] ret = new ConsecutiveIntegersAddedUpToS_57_II().findContinuousSequence(target);

            System.out.println("[");
            if (ret == null) {
                System.out.println("null");
            } else {
                if(ret.length != 0) {
                    for(int[] e : ret) {
                        String out = int2dArrayToString(e);
                        System.out.println(out);
                    }
                }
            }
            System.out.println("]");
        }
    }

    /*
     * Version 1, sliding window.
     */
    public int[][] findContinuousSequence(int target) {
        int s = 1, e = 2;
        List<List<Integer>> resList = new ArrayList<>();
        while(s <= target / 2) {
            int sum = (e + s) * (e - s + 1) / 2;
            if(sum == target) {
                List<Integer> entry = new ArrayList<>();
                for(int i = s; i <= e; i++) {
                    entry.add(i);
                }
                resList.add(entry);
                s++;
                e = s + 1;
            } else if(sum < target) {
                e++;
            } else {
                s++;
                e = s + 1;
            }
        }
        int[][] result = new int[resList.size()][];
        for(int i = 0; i < result.length; i++) {
            List<Integer> entry = resList.get(i);
            result[i] = new int[entry.size()];
            for(int j = 0; j < result[i].length; j++) {
                result[i][j] = entry.get(j);
            }
        }
        return result;
    }
}
