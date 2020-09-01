import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。
 * 假设输入的数组的任意两个数字都互不相同。
 * <p>
 * 提示：数组长度 <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PostorderTraversalOfBST_33 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if(input.length() == 0) {
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
        while((line = in.readLine()) != null) {
            int[] postorder = stringToIntegerArray(line);

            boolean ret = new PostorderTraversalOfBST_33().verifyPostorder(postorder);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int begin, int end) {
        if(begin == end) {
            return true;
        }
        int rightChildBegin = -1;
        for(int i = begin; i < end; i++) {
            if(rightChildBegin == -1 && postorder[i] > postorder[end]) {
                rightChildBegin = i;
            }
            if(rightChildBegin != -1 && postorder[i] < postorder[end]) {
                return false;
            }
        }
        if(rightChildBegin == -1) {
            rightChildBegin = end;
        }
        int childBegin = rightChildBegin, childEnd = end - 1;
        if(childBegin <= childEnd) {
            if(!verifyPostorder(postorder, childBegin, childEnd)) {
                return false;
            }
        }
        childBegin = begin;
        childEnd = rightChildBegin - 1;
        if(childBegin <= childEnd) {
            return verifyPostorder(postorder, childBegin, childEnd);
        }
        return true;
    }
}
