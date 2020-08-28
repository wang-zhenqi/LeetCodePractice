import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * There is a robot starting at position (0, 0), the origin, on a 2D plane.
 * Given a sequence of its moves, judge if this robot ends up at (0, 0) after
 * it completes its moves.
 *
 * The move sequence is represented by a string, and the character moves[i]
 * represents its ith move. Valid moves are R (right), L (left), U (up), and
 * D (down). If the robot returns to the origin after it finishes all of its
 * moves, return true. Otherwise, return false.
 *
 * Note: The way that the robot is "facing" is irrelevant. "R" will always make
 * the robot move to the right once, "L" will always make it move left, etc.
 * Also, assume that the magnitude of the robot's movement is the same for each
 * move.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/robot-return-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RobotReturnToOrigin_657 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            boolean ret = new RobotReturnToOrigin_657().judgeCircle(line);
            System.out.print(ret);
        }
    }

    public boolean judgeCircle(String moves) {
        if(moves == null || moves.isEmpty()) {
            return true;
        }
        int x = 0, y = 0;
        for(int i = 0; i < moves.length(); i++) {
            switch(moves.charAt(i)) {
                case 'U':
                    y--;
                    break;
                case 'D':
                    y++;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                default:
                    return false;
            }
        }
        return (x == 0 && y == 0);
    }
}
