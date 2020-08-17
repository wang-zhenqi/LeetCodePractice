/*
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 示例：
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 */

class SearchIn2DArr_04 {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) {
        int[][] matrix = {{-5}};
        int target = -5;

        boolean ret = new SearchIn2DArr_04().findNumberIn2DArray(matrix, target);

        String out = booleanToString(ret);

        System.out.println(out);
    }

    /*
     * Version 2
     * Search from the upper right corner (or lower left corner).
     * Decide going down or left by comparing the current element
     * until finally find the number.
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rowNumber = matrix.length;
        if(rowNumber == 0) {
            return false;
        }

        int columnNumber = matrix[0].length;
        if(columnNumber == 0) {
            return false;
        }

        int rowIndex = 0, columnIndex = columnNumber - 1;
        while(columnIndex >= 0 && rowIndex <= rowNumber - 1) {
            if(target == matrix[rowIndex][columnIndex]) {
                return true;
            }
            if(target < matrix[rowIndex][columnIndex]) {
                columnIndex--;
                continue;
            }
            if(target > matrix[rowIndex][columnIndex]) {
                rowIndex++;
            }
        }
        return false;
    }
}