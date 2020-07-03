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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SearchIn2DArr_04 {
    private int[][] ori_matrix;
    private int target;
    private boolean ans;

    /*
     * Version 1
     * Binary search on 2D
     * In each iteration, pick up the middle point, then divide the whole
     * matrix into two. For each sub-matrix, repeat the process recursively.
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length == 0) {
            return false;
        }
        for(int[] ele : matrix) {
            if(ele.length == 0) {
                return false;
            }
        }
        this.target = target;
        this.ori_matrix = matrix;
        this.ans = false;

        int[] upperleft = {0, 0};
        int[] lowerright = {matrix.length - 1, matrix[0].length - 1};
        findNumberInSubArr(upperleft, lowerright);
        return ans;
    }
    private void findNumberInSubArr(int[] p1, int[] p2) {
        if(ans) {
            return;
        }
        int[] midPoint = getMidPoint(p1, p2);
        if(target == ori_matrix[midPoint[0]][midPoint[1]]) {
            ans = true;
            return;
        }
        //split the array: sub1(p11, p12), sub2(p21, p22);
        int[][] sections = split(p1, p2, midPoint);
        if(sections[0][0] != -1) {
            int[] vet1 = new int[2];
            int[] vet2 = new int[2];
            vet1[0] = sections[0][0];
            vet1[1] = sections[0][1];
            vet2[0] = sections[0][2];
            vet2[1] = sections[0][3];
            findNumberInSubArr(vet1, vet2);
        }
        if(sections[1][0] != -1) {
            int[] vet1 = new int[2];
            int[] vet2 = new int[2];
            vet1[0] = sections[1][0];
            vet1[1] = sections[1][1];
            vet2[0] = sections[1][2];
            vet2[1] = sections[1][3];
            findNumberInSubArr(vet1, vet2);
        }
    }

    private int[] getMidPoint(int[] p1, int[] p2) {
        int[] coordinary = new int[2];
        coordinary[0] = (p2[0] + p1[0] + 1) / 2;
        coordinary[1] = (p2[1] + p1[1] + 1) / 2;
        return coordinary;
    }

    private int[][] split(int[] p1, int[] p2, int[] mp) {
        int[][] subArrayPoints = new int[2][4];
        if(ori_matrix[mp[0]][mp[1]] < target) {
            if(mp[1] == p2[1]) {
                subArrayPoints[0][0] = -1;
                subArrayPoints[0][1] = -1;
                subArrayPoints[0][2] = -1;
                subArrayPoints[0][3] = -1;
            }
            else {
                subArrayPoints[0][0] = p1[0];
                subArrayPoints[0][1] = mp[1] + 1;
                subArrayPoints[0][2] = mp[0];
                subArrayPoints[0][3] = p2[1];
            }
            if(mp[0] == p2[0]) {
                subArrayPoints[1][0] = -1;
                subArrayPoints[1][1] = -1;
                subArrayPoints[1][2] = -1;
                subArrayPoints[1][3] = -1;
            }
            else {
                subArrayPoints[1][0] = mp[0] + 1;
                subArrayPoints[1][1] = p1[1];
                subArrayPoints[1][2] = p2[0];
                subArrayPoints[1][3] = p2[1];
            }
        }
        else {
            if(mp[1] == p1[1]) {
                subArrayPoints[0][0] = -1;
                subArrayPoints[0][1] = -1;
                subArrayPoints[0][2] = -1;
                subArrayPoints[0][3] = -1;
            }
            else {
                subArrayPoints[0][0] = p1[0];
                subArrayPoints[0][1] = p1[1];
                subArrayPoints[0][2] = p2[0];
                subArrayPoints[0][3] = mp[1] - 1;
            }
            if(mp[0] == p1[0]) {
                subArrayPoints[1][0] = -1;
                subArrayPoints[1][1] = -1;
                subArrayPoints[1][2] = -1;
                subArrayPoints[1][3] = -1;
            }
            else {
                subArrayPoints[1][0] = p1[0];
                subArrayPoints[1][1] = mp[1];
                subArrayPoints[1][2] = mp[0] - 1;
                subArrayPoints[1][3] = p2[1];
            }
        }
        return subArrayPoints;
    }
}

class TestClass_04 {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[][] matrix = {{}};
        int target = 1;

        boolean ret = new SearchIn2DArr_04().findNumberIn2DArray(matrix, target);

        String out = booleanToString(ret);

        System.out.print(out);
    }
}