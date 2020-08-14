import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting adjacent
 * lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfIslands_200 {
    public static void main(String[] args) {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        char[][] grid3 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };

        char[][] grid4 = {
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}
        };

        char[][] grid5 = {
                {'1'}
        };
        long s = System.currentTimeMillis();
        int res = new NumberOfIslands_200().numIslands(grid1);
        long e = System.currentTimeMillis();
        System.out.println(res + " (" + (e - s) + ")");
    }

    private void bfs(char[][] grids, Coord coord) {
        Queue<Coord> queue = new LinkedList<>();
        queue.add(coord);
        grids[coord.row][coord.col] = '0';
        while(!queue.isEmpty()) {
            Coord c = queue.poll();
            if(c.row > 0 && grids[c.row - 1][c.col] == '1') {
                queue.add(new Coord(c.row - 1, c.col));
                grids[c.row - 1][c.col] = '0';
            }
            if(c.col < grids[0].length - 1 && grids[c.row][c.col + 1] == '1') {
                queue.add(new Coord(c.row, c.col + 1));
                grids[c.row][c.col + 1] = '0';
            }
            if(c.row < grids.length - 1 && grids[c.row + 1][c.col] == '1') {
                queue.add(new Coord(c.row + 1, c.col));
                grids[c.row + 1][c.col] = '0';
            }
            if(c.col > 0 && grids[c.row][c.col - 1] == '1') {
                queue.add(new Coord(c.row, c.col - 1));
                grids[c.row][c.col - 1] = '0';
            }
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        /*
         * Version 1, Flood Fill + BFS.
         * This method will cause OT or OOM, unless the visited position is
         * marked as soon as it is added to the queue.
         * It's a good way to practice BFS.
         * It's time complexity is O(N^2).
         */
        int res = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '1') {
                    res++;
                    bfs(grid, new Coord(i, j));
                }
            }
        }
        return res;

        /*
         * Version 2, Union find.
         */
        /*int[] rowMove = {-1, 1, 0, 0};
        int[] colMove = {0, 0, -1, 1};

        UnionFind unionFind = new UnionFind(grid);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '0') {
                    continue;
                }
                for(int k = 0; k < 4; k++) {
                    int neighborRow = i + rowMove[k];
                    int neighborCol = j + colMove[k];
                    if(neighborRow >= 0 && neighborCol >= 0 &&
                        neighborRow <= rows - 1 && neighborCol <= cols - 1 &&
                        grid[neighborRow][neighborCol] == '1') {
                        unionFind.Union(i * cols + j, neighborRow * cols + neighborCol);
                    }
                }
            }
        }

        return unionFind.numOfRoots;*/
    }

    private class Coord {
        int row;
        int col;

        Coord(int r, int c) {
            row = r;
            col = c;
        }
    }

    private class UnionFind {
        int[] roots;
        int numOfElements;
        int numOfRoots;
        int rows;
        int cols;

        UnionFind(char[][] elements) {
            rows = elements.length;
            cols = elements[0].length;
            numOfElements = rows * cols;
            numOfRoots = 0;
            roots = new int[numOfElements];
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if(elements[i][j] == '1') {
                        roots[i * cols + j] = i * cols + j;
                        numOfRoots++;
                    } else {
                        roots[i * cols + j] = -1;
                    }
                }
            }
        }

        private int findRoot(int index) {
            //int i = index;
            while(roots[index] != index) {
                index = roots[index];
            }
            /*while(roots[i] != i) {
                int tmp = roots[i];
                roots[i] = index;
                i = tmp;
            }*/
            return index;
        }

        private void Union(int e1, int e2) {
            int root1 = findRoot(e1);
            int root2 = findRoot(e2);
            if(root1 > root2) {
                roots[root1] = root2;
                numOfRoots--;
            } else if(root1 < root2) {
                roots[root2] = root1;
                numOfRoots--;
            }
        }
    }
}
