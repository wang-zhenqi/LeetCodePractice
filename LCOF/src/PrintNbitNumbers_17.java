import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * <p>
 * 说明：
 * <p>
 * 用返回一个整数列表来代替打印
 * n 为正整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintNbitNumbers_17 {
    public static void main(String[] args) throws IOException {
        new PrintNbitNumbers_17().printNumbers(10);
        /*int[] array = new PrintNbitNumbers_17().printNumbers(3);
        System.out.print("[");
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + (i == array.length - 1 ? "" : ", "));
        }
        System.out.println("]");*/
    }

    /*public int[] printNumbers(int n) {
        int[] array = new int[(int)(Math.pow(10, n)) - 1];
        for(int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array;
    }*/

    public void printNumbers(int n) throws IOException {
        BigInt number = new BigInt(n);
        int total = (int) (Math.pow(10, n) - 1);
        PrintStream ps = new PrintStream(new FileOutputStream(new File("./big_ints.txt")));

        for(int i = 0; i < total; i++) {
            number.addOne();
            ps.print(number.toString());
            //number.printNumber();
            if(i % 10 < 9) {
                //System.out.print(" ");
                ps.print(" ");
            } else {
                //System.out.println();
                ps.println();
            }
        }
    }

    private static class BigInt {
        int bits;
        byte[] number;
        int highestIndex;

        //little endian
        BigInt(int b) {
            bits = b;
            number = new byte[bits];
            highestIndex = 0;
        }

        void addOne() {
            byte carry = 1;
            int i = 0;
            do {
                number[i] = (byte) (number[i] + carry);
                carry = (byte) (number[i] / 10);
                number[i] = (byte) (number[i] % 10);
                if(++i > highestIndex && carry > 0) {
                    highestIndex++;
                }
            } while(carry > 0);
        }

        void printNumber() {
            for(int i = highestIndex; i >= 0; i--) {
                System.out.print(number[i]);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i = highestIndex; i >= 0; i--) {
                sb.append(number[i]);
            }
            return sb.toString();
        }
    }
}
