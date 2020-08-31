import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * There are N rooms and you start in room 0. Each room has a distinct number in
 * 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 * <p>
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is
 * an integer in [0, 1, ..., N-1] where N = rooms.length. A key rooms[i][j] = v
 * opens the room with number v.
 * <p>
 * Initially, all the rooms start locked (except for room 0).
 * <p>
 * You can walk back and forth between rooms freely.
 * <p>
 * Return true if and only if you can enter every room.
 * <p>
 * Note:
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * The number of keys in all rooms combined is at most 3000.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keys-and-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KeysAndRooms_841 {
    public static List<List<Integer>> stringToInt2dList(String input) {
        input = input.substring(1, input.length() - 1);
        String[] room_keys = input.split("],\\[");

        List<List<Integer>> list = new ArrayList<>();
        for(String room_key : room_keys) {
            if(room_key != null && !room_key.isEmpty()) {
                if(room_key.charAt(0) == '[') {
                    room_key = room_key.substring(1);
                }
                if(!room_key.isEmpty() && room_key.charAt(room_key.length() - 1) == ']') {
                    room_key = room_key.substring(0, room_key.length() - 1);
                }
                List<Integer> room = new ArrayList<>();
                if(!room_key.isEmpty()) {
                    String[] keys = room_key.split(",");
                    for(String key : keys) {
                        if(key != null && !key.isEmpty()) {
                            room.add(Integer.parseInt(key));
                        }
                    }
                }
                list.add(room);
            }
        }
        return list;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            List<List<Integer>> rooms = stringToInt2dList(line);

            boolean ret = new KeysAndRooms_841().canVisitAllRooms(rooms);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms == null || rooms.isEmpty()) {
            return false;
        }
        HashSet<Integer> visitedRooms = new HashSet<>();
        dfs(0, visitedRooms, rooms);
        return visitedRooms.size() == rooms.size();
    }

    private void dfs(int roomNum, HashSet<Integer> visitedRooms, List<List<Integer>> rooms) {
        List<Integer> room_keys = rooms.get(roomNum);
        visitedRooms.add(roomNum);
        if(room_keys.isEmpty()) {
            return;
        }
        for(Integer room_key : room_keys) {
            if(!visitedRooms.contains(room_key)) {
                dfs(room_key, visitedRooms, rooms);
            }
        }
    }
}
