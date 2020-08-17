import java.util.HashSet;

/**
 * An image is represented by a 2-D array of integers, each integer representing
 * the pixel value of the image (from 0 to 65535).
 * <p>
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of
 * the flood fill, and a pixel value newColor, "flood fill" the image.
 * <p>
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected
 * 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same
 * color as the starting pixel), and so on. Replace the color of all of the
 * aforementioned pixels with the newColor.
 * <p>
 * At the end, return the modified image.
 * <p>
 * Note:
 * <p>
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and
 * 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in
 * [0, 65535].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flood-fill
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FloodFill_733 {
    private final int[] dr = {1, -1, 0, 0};
    private final int[] dc = {0, 0, 1, -1};
    HashSet<Integer> visited;
    private int[][] newImage;
    private int newColor;
    private int oldColor;

    public static void main(String[] args) {
        /*int[][] image = {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };*/
        int[][] image = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int sr = 0;
        int sc = 0;
        int newColor = 2;
        int[][] result = new FloodFill_733().floodFill(image, sr, sc, newColor);

        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[0].length; j++) {
                System.out.print(result[i][j] + ((j == image[0].length - 1) ? "" : " "));
            }
            System.out.println();
        }
    }

    private void dfs(int sr, int sc) {
        if(sr < 0 || sr >= newImage.length ||
                sc < 0 || sc >= newImage[0].length ||
                newImage[sr][sc] != oldColor ||
                visited.contains(sr * newImage[0].length + sc)) {
            return;
        }

        visited.add(sr * newImage[0].length + sc);
        newImage[sr][sc] = newColor;

        for(int i = 0; i < 4; i++) {
            dfs(sr + dr[i], sc + dc[i]);
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        /*
         * Typical flood fill algorithm.
         * If the problem wants to group the different colors, Union Find will be
         * a good way.
         */
        newImage = image;
        this.newColor = newColor;
        this.oldColor = image[sr][sc];
        visited = new HashSet<>();
        dfs(sr, sc);
        return newImage;
    }
}
