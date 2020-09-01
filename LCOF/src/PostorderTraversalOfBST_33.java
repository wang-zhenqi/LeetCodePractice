import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 * 提示：数组长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PostorderTraversalOfBST_33 {
    private int[] postorder;
    private int[] inorder;
    private int postIdx;

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] postorder = stringToIntegerArray(line);

            boolean ret = new PostorderTraversalOfBST_33().verifyPostorder(postorder);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length == 0) {
            return true;
        }
        this.postorder = postorder;
        inorder = Arrays.copyOf(postorder, postorder.length);
        Arrays.sort(inorder);
        postIdx = postorder.length - 1;

        return (findRoot(0, inorder.length - 1));
    }

    private boolean findRoot(int inorderBegin, int inorderEnd) {
        int rootIdx = -1;
        for(int i = inorderBegin; i <= inorderEnd; i++) {
            if(inorder[i] == postorder[postIdx]) {
                rootIdx = i;
                break;
            }
        }
        if(rootIdx == -1) {
            return false;
        }
        int rChildLeft = rootIdx + 1, rChildRight = inorderEnd;
        if(rChildLeft <= rChildRight && postIdx - 1 >= 0){
            postIdx--;
            if(!findRoot(rChildLeft, rChildRight)) {
                return false;
            }
        }
        rChildLeft = inorderBegin;
        rChildRight = rootIdx - 1;
        if(rChildLeft <= rChildRight && postIdx - 1 > 0) {
            postIdx--;
            return findRoot(rChildLeft, rChildRight);
        }
        return true;
    }
}
