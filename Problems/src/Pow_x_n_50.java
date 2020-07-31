import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * Implement pow(x, n), which calculates x raised to the power n (x^n).
 */
public class Pow_x_n_50 {
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1.0;
        }
        if(x == 1.0 || n == 1) {
            return x;
        }
        double result = 1.0;
        if(n < 0) {
            x = 1 / x;
            result *= x;
            n = -(n + 1);
        }

        while(n > 0) {
            if((n & 1) == 1) {
                result *= x;
            }
            x *= x;
            n >>= 1;
        }
        return result;
    }

    private static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            double x = Double.parseDouble(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            double ret = new Pow_x_n_50().myPow(x, n);

            String out = doubleToString(ret);

            System.out.print(out);
        }
    }
}
