import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists
 * in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used
 * item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LRUCache_146 {
    //static int count = 0;
    private final int capacity;
    private final Item[] cacheBody;
    private final HashMap<Integer, Item> keyItemMap;
    private Item head;
    private Item tail;
    private int size;

    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        this.cacheBody = new Item[capacity];
        for(int i = 0; i < capacity; i++) {
            cacheBody[i] = new Item();
        }
        keyItemMap = new HashMap<>();
        size = 0;
    }

    public static void main(String[] args) {
        /*LRUCache_146 cache = new LRUCache_146(5);

        cache.put(1, 10);
        cache.put(2, 23);
        System.out.println("get(1): " + cache.get(1));
        cache.put(3, 34);
        System.out.println("get(2): " + cache.get(2));
        cache.put(4, 45);
        System.out.println("get(4): " + cache.get(4));
        cache.put(5, 59);
        cache.put(3, 36);
        System.out.println("get(3): " + cache.get(3));
        cache.put(7, 77);
        cache.put(6, 65);
        System.out.println("get(7): " + cache.get(7));
        cache.put(5, 58);
        System.out.println("get(6): " + cache.get(6));
        System.out.println("get(5): " + cache.get(5));
        System.out.println("get(1): " + cache.get(1));
        System.out.println("get(2): " + cache.get(2));*/

        /*LRUCache_146 cache = new LRUCache_146(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1): " + cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println("get(2): " + cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println("get(1): " + cache.get(1));       // returns -1 (not found)
        System.out.println("get(3): " + cache.get(3));       // returns 3
        System.out.println("get(4): " + cache.get(4));       // returns 4*/

        /*LRUCache_146 cache = new LRUCache_146(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println("get(4): " + cache.get(4));
        System.out.println("get(3): " + cache.get(3));
        System.out.println("get(2): " + cache.get(2));
        System.out.println("get(1): " + cache.get(1));
        cache.put(5, 5);
        System.out.println("get(1): " + cache.get(1));
        System.out.println("get(2): " + cache.get(2));
        System.out.println("get(3): " + cache.get(3));
        System.out.println("get(4): " + cache.get(4));
        System.out.println("get(5): " + cache.get(5));*/

        /*LRUCache_146 cache = new LRUCache_146(10);
        cache.put(7, 28);   // 7
        cache.put(7, 1);    // 7
        cache.put(8, 15);   // 7 8
        System.out.println("get(6): " + cache.get(6));  // 7 8
        cache.put(10, 27);  // 7 8 10
        cache.put(8, 10);   // 7 10 8
        System.out.println("get(8): " + cache.get(8));  //7 10 8
        cache.put(6, 29);   // 7 10 8 6
        cache.put(1, 9);    // 7 10 8 6 1
        System.out.println("get(6): " + cache.get(6));  // 7 10 8 1 6
        cache.put(10, 7);   // 7 8 6 1 10
        System.out.println("get(1): " + cache.get(1));  // 7 8 6 10 1
        System.out.println("get(2): " + cache.get(2));  // 7 8 6 10 1
        System.out.println("get(13): " + cache.get(13));// 7 8 6 10 1
        cache.put(8, 30);   // 7 6 10 1 8
        cache.put(1, 5);    // 7 6 10 8 1
        System.out.println("get(1): " + cache.get(1));  // 7 6 10 8 1
        cache.put(13, 2);   // 7 6 10 8 1 13
        System.out.println("get(12): " + cache.get(12));// 7 6 10 8 1 13*/

        String[] operations = {"LRUCache", "put", "put", "put", "put", "put", "get", "put", "get", "get", "put", "get", "put", "put", "put", "get", "put", "get", "get", "get", "get", "put", "put", "get", "get", "get", "put", "put", "get", "put", "get", "put", "get", "get", "get", "put", "put", "put", "get", "put", "get", "get", "put", "put", "get", "put", "put", "put", "put", "get", "put", "put", "get", "put", "put", "get", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put", "get", "get", "get", "put", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "get", "get", "get", "put", "put", "put", "get", "put", "put", "put", "get", "put", "put", "put", "get", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "put", "put", "put"};
        int[] data = {10, 10, 13, 3, 17, 6, 11, 10, 5, 9, 10, 13, 2, 19, 2, 3, 5, 25, 8, 9, 22, 5, 5, 1, 30, 11, 9, 12, 7, 5, 8, 9, 4, 30, 9, 3, 9, 10, 10, 6, 14, 3, 1, 3, 10, 11, 8, 2, 14, 1, 5, 4, 11, 4, 12, 24, 5, 18, 13, 7, 23, 8, 12, 3, 27, 2, 12, 5, 2, 9, 13, 4, 8, 18, 1, 7, 6, 9, 29, 8, 21, 5, 6, 30, 1, 12, 10, 4, 15, 7, 22, 11, 26, 8, 17, 9, 29, 5, 3, 4, 11, 30, 12, 4, 29, 3, 9, 6, 3, 4, 1, 10, 3, 29, 10, 28, 1, 20, 11, 13, 3, 3, 12, 3, 8, 10, 9, 3, 26, 8, 7, 5, 13, 17, 2, 27, 11, 15, 12, 9, 19, 2, 15, 3, 16, 1, 12, 17, 9, 1, 6, 19, 4, 5, 5, 8, 1, 11, 7, 5, 2, 9, 28, 1, 2, 2, 7, 4, 4, 22, 7, 24, 9, 26, 13, 28, 11, 26};
        LRUCache_146 cache = null;
        int j = 0;
        for(int i = 0; i < operations.length; i++, j++) {
            switch(operations[i]) {
                case "LRUCache":
                    cache = new LRUCache_146(data[j]);
                    break;
                case "put":
                    cache.put(data[j], data[++j]);
                    break;
                case "get":
                    System.out.println("get(" + data[j] + "): " + cache.get(data[j]));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operations[i]);
            }
        }
    }

    private void visitItem(Item item) {
        if(item != head) {
            Item previousItem = item.prev;
            Item nextItem = item.next;

            nextItem.prev = previousItem;
            if(previousItem != null) {
                previousItem.next = nextItem;
            }

            item.next = null;
            item.prev = head;

            head.next = item;

            head = item;
            if(item == tail) {
                tail = nextItem;
            }
        }
        //printState();
    }

    /*private void printState() {
        Item p = tail;
        System.out.println("====== " + count++ + " ======");
        while(p != null) {
            System.out.println("(" + p.key + ", " + p.value + ", " + p.prev + ", " + p.next + ")");
            p = p.next;
        }
        System.out.println("\n");
    }*/

    public int get(int key) {
        if(keyItemMap.containsKey(key)) {
            Item item = keyItemMap.get(key);
            visitItem(item);
            return item.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(keyItemMap.containsKey(key)) {
            Item item = keyItemMap.get(key);
            visitItem(item);
            item.value = value;
        } else {
            if(size < capacity) {
                cacheBody[size].set(key, value);
                cacheBody[size].prev = size > 0 ? head : null;
                cacheBody[size].next = null;
                if(head != null) {
                    head.next = cacheBody[size];
                }
                head = cacheBody[size];
                if(size == 0) {
                    tail = cacheBody[0];
                }
                keyItemMap.put(key, cacheBody[size++]);
            } else {
                Item item = tail;
                keyItemMap.remove(item.key);
                item.set(key, value);

                if(head != tail) {
                    tail = tail.next;
                    tail.prev = null;

                    item.prev = head;
                    item.next = null;
                    head.next = item;

                    head = item;
                }
                keyItemMap.put(key, item);
            }
        }
        //printState();
    }

    private class Item {
        int key;
        int value;
        Item next;
        Item prev;

        void set(int k, int v) {
            key = k;
            value = v;
        }
    }
}
