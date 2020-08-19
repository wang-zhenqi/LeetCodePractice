/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintNbitNumbers_17 {
    public static void main(String[] args) {
        int[] array = new PrintNbitNumbers_17().printNumbers(3);
        System.out.print("[");
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + (i == array.length - 1 ? "" : ", "));
        }
        System.out.println("]");
    }

    public int[] printNumbers(int n) {
        int[] array = new int[(int)(Math.pow(10, n)) - 1];
        for(int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array;
    }
}
