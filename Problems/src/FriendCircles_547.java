import java.util.LinkedList;
import java.util.Queue;

/**
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend
 * of B, and B is a direct friend of C, then A is an indirect friend of C. And we
 * defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in
 * the class. If M[i][j] = 1, then the ith and jth students are direct friends with
 * each other, otherwise not. And you have to output the total number of friend
 * circles among all the students.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FriendCircles_547 {
    public static void main(String[] args) {
        int[][] M1 = {      // res = 2
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };

        int[][] M2 = {      // res = 1
                {1,1,0},
                {1,1,1},
                {0,1,1}
        };

        int[][] M3 = {      // res = 1
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1}
        };

        int[][] M4 = {      // res = 0

        };

        int[][] M5 = {      // res = 1
                {1}
        };

        int[][] M6 = {      // res = 1
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}
        };
        int res = new FriendCircles_547().findCircleNum(M1);
        System.out.println(res);
    }

    private void dfs(int[][] M, int person) {
        for(int i = 0; i <= M.length - 1; i++) {
            if(M[person][i] == 1) {
                M[person][i] = M[i][person] = 0;
                dfs(M, i);
            }
        }
    }

    private void bfs(int[][] M, int person) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(person);
        while(!queue.isEmpty()) {
            int p = queue.poll();
            for(int i = 0; i < M.length; i++) {
                if(M[p][i] == 1) {
                    queue.add(i);
                    M[p][i] = M[i][p] = 0;
                }
            }
        }
    }

    /**
     * This problem is almost the same as problem 200 - number of islands.
     * It can be solved by DFS, BFS, and Union find.
     * @param M the friend circle relations.
     * @return number of friend circles.
     */
    public int findCircleNum(int[][] M) {
        if(M == null) {
            return 0;
        }

        int N = M.length;
        if(N == 0) {
            return 0;
        }

        /*
         * Version 1, DFS.
         */
        /*int res = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(M[i][j] == 1) {
                    res++;
                    M[i][j] = M[j][i] = 0;
                    dfs(M, i);
                }
            }
        }
        return res;*/

        /*
         * Version 2, BFS.
         */
        /*int res = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j <= i; j++) {
                if(M[i][j] == 1) {
                    res++;
                    M[i][j] = M[j][i] = 0;
                    bfs(M, i);
                }
            }
        }
        return res;*/

        /*
         * Version 3, Union find.
         */
        UnionFind unionFind = new UnionFind(N);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j <= i; j++) {
                if(M[i][j] == 1) {
                    unionFind.Union(i, j);
                }
            }
        }
        return unionFind.numOfRoots;
    }

    private class UnionFind {
        int[] roots;
        int numOfRoots;

        UnionFind(int n) {
            roots = new int[n];
            for(int i = 0; i < n; i++) {
                roots[i] = i;
            }
            numOfRoots = n;
        }

        public int findRoot(int x) {
            while(roots[x] != x) {
                x = roots[x];
            }
            return x;
        }

        public void Union(int x, int y) {
            int rootx = findRoot(x);
            int rooty = findRoot(y);
            if(rootx > rooty) {
                roots[rootx] = rooty;
                numOfRoots--;
            } else if(rootx < rooty) {
                roots[rooty] = rootx;
                numOfRoots--;
            }
        }
    }
}
