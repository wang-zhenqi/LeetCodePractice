import java.util.ArrayList;
import java.util.List;
/**
 * Given a matrix of m x n elements (m rows, n columns), return all 
 * elements of the matrix in spiral order.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SpiralMatrix_54 {
    public static void main(String[] args) {
        int[][][] matrices = {
                {
                        {1,2,3},
                        {4,5,6},
                        {7,8,9}
                },
                {
                        {1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12}
                }
        };
        SpiralMatrix_54 obj = new SpiralMatrix_54();
        StringBuilder sb = new StringBuilder();
        for(int[][] matrix : matrices) {
            List<Integer> result = obj.spiralOrder(matrix);
            if(sb.length() > 0) {
                sb.delete(0, sb.length());
            }
            sb.append('[');
            for(Integer r : result) {
                sb.append(r).append(',');
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');
            System.out.println(sb.toString());
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        if(rows == 0) {
            return new ArrayList<>();
        }
        int cols = matrix[0].length;
        if(cols == 0) {
            return new ArrayList<>();
        }
        int total = rows * cols;
        int[] bound = {cols, rows, -1, 0};
        int[][] direction = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        int flag = 0, r = 0, c = 0;
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < total; i++) {
            result.add(matrix[r][c]);
            r += direction[flag][0];
            c += direction[flag][1];
            if(flag == 0 && c == bound[flag]) {
                bound[flag]--;
                flag = 1;
                r++;
                c--;
            }
            if(flag == 1 && r == bound[flag]) {
                bound[flag]--;
                flag = 2;
                r--;
                c--;
            }
            if(flag == 2 && c == bound[flag]) {
                bound[flag]++;
                flag = 3;
                r--;
                c++;
            }
            if(flag == 3 && r == bound[flag]) {
                bound[flag]++;
                flag = 0;
                r++;
                c++;
            }
        }
        return result;
    }
}
