/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintMatrixClockwisely_29 {
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
        PrintMatrixClockwisely_29 obj = new PrintMatrixClockwisely_29();
        StringBuilder sb = new StringBuilder();
        for(int[][] matrix : matrices) {
            int[] result = obj.spiralOrder(matrix);
            if(sb.length() > 0) {
                sb.delete(0, sb.length());
            }
            sb.append('[');
            for(int r : result) {
                sb.append(r).append(',');
            }
            if(sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(']');
            System.out.println(sb.toString());
        }
    }

    public int[] spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        if(rows == 0) {
            return new int[0];
        }
        int cols = matrix[0].length;
        if(cols == 0) {
            return new int[0];
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
        int[] result = new int[total];
        for(int i = 0; i < total; i++) {
            result[i] = matrix[r][c];
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
