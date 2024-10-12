# Summary of the problems

## Array and Linked List
Array and linked list are the basic linearly organized data structure.

| Operation | Array | Linked List |
| :---: | :---: | :---: |
| Insertion | O(N) | O(1) |
| Deletion | O(N) | O(1) |
| Location | O(1) | O(N) |
| Searching | O(N) | O(N) |

### Problems
#### [206. Reverse Linked List](https://github.com/wang-zhenqi/LeetCodePractice/blob/Java/Problems/src/ReverseLinkedList_206.java)
Make every node points to its previous one. To trace the whole chain, the next one needs to be noted. Otherwise the chain will be broken.

````
1 -> 2 -> 3 -> null

# init:
prev = null
cur = 1
# movements:
while cur != null:
    next = cur.next     # next = 2
    cur.next = prev     # 1 -> null
    prev = cur          # prev = 1
    cur = next          # cur = 2
    # null <- 1, 2 -> 3 -> null
````

#### [141. Linked List Cycle](https://github.com/wang-zhenqi/LeetCodePractice/blob/Java/Problems/src/LinkedListCycle_141.java)
There are at least two ways to solve it:
1. Traverse from the beginning of the list, memorizing each node when pass it. When it encounters a node that's been past, the start of the cycle is found.

    To accomplish this, a hash set will make it quicker: if the node is already in the set, then it won't be added in again.

    The time complexity for traversing is O(N), for searching and adding node is O(1), so the overall time complexity is O(N).

    The space complexity is O(N).

    ````
    foreach node in list:
        if add node to set succeed:
            next node.
        else:
            return true
    return false
    ````

2. Tortoise and hare. Use two pointers, one forwards 1 node/step, the other 2 nodes/step. If the linked list has cycle, then they will meet again.

    ````
    while tortoise and hare not reach the end:
        tortoise = tortoise.next
        hare = hare.next.next
        if tortoise == hare:
            return true
    return false
    ````

#### [142. Linked List Cycle II](https://github.com/wang-zhenqi/LeetCodePractice/blob/Java/Problems/src/LinkedListCycleII_142.java)
This problem is a little more complicated than the problem 141. Because it requires to find the start of the cycle (if it exists).

The method using a hash set is a good way, actually, it can solve both problems.

However, if you observe the mathematical feature, the tortoise and hare method can be better, for it requires less space.

If there is a cycle in the linked list, how long will it take the tortoise to reach the start of the cycle?

Assume that from the Head to the node before the start of the cycle, there are *l* nodes; and there are *c* nodes in the cycle.
So the tortoise needs *l* + n*c* (n = 0, 1, 2, ...) steps to get to the start of the cycle.

For the first time tortoise and hare meet at the *kth* node in the cycle, they take *s* and 2*s* steps respectively. Then we have:

* *s* = *l* + i*c* + *k*
* 2*s* = *l* + j*c* + *k* (j - i > 1)
* so, *s* = (j - i)*c*, which means if the tortoise goes *l* steps farther, it will have gone *l* + (j - i)*c* step, reaching the start of the cycle.

````
while tortoise and hare not reach the end:
    tortoise = tortoise.next
    hare = here.next.next
    if tortoise == hare:
        while head != tortoise:
            head = head.next
            tortoise = tortoise.next
        return tortoise
return null
````

## Stack and Queue
