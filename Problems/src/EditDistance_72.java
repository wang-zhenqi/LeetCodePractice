/**
 * Given two words word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EditDistance_72 {
    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "nation";

        int ret = new EditDistance_72().minDistance(word1, word2);

        String out = String.valueOf(ret);

        System.out.print(out);
    }

    public int minDistance(String word1, String word2) {
        /*
         * DP. This problem is hard for me to come up with the state definition.
         * So maybe I should memorise this method.
         *
         * dp[i][j] represents the minimum distance when the first i letters of word1
         * and the first j letters of word2 get equal.
         *
         * Now think about an arbitrary dp[i][j], there are two possibilities:
         * 1. word1[i] = word2[j], then dp[i][j] = dp[i-1][j-1]
         * 2. else. Then the minimum distance has to be a previous state plus 1
         * operation, it depends on which previous state has the minimum value. So,
         * dp[i][j] = 1 + min{dp[i-1][j], dp[i][j-1], dp[i-1][j-1]}
         *
         * Initialise:
         * dp[i][0] = i
         * dp[0][j] = j
         */
        int len1 = word1.length(), len2 = word2.length();
        if(len1 == 0 && len2 == 0) {
            return 0;
        }

        int[][] miniDis = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++) {
            miniDis[i][0] = i;
        }
        for(int j = 1; j <= len2; j++) {
            miniDis[0][j] = j;
        }

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    miniDis[i][j] = miniDis[i - 1][j - 1];
                } else {
                    miniDis[i][j] = 1 + Math.min(miniDis[i - 1][j], // word1.insert
                                        Math.min(miniDis[i][j - 1], // word1.delete
                                            miniDis[i - 1][j - 1]));// word1.replace
                }
            }
        }
        return miniDis[len1][len2];
    }
}
