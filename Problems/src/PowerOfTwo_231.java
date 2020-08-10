import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an integer, write a function to determine if it is a power of two.
 */
public class PowerOfTwo_231 {
    private static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            boolean ret = new PowerOfTwo_231().isPowerOfTwo(n);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
