"""
旋转链表
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
示例 1：
输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]

示例 2：
输入：head = [0,1,2], k = 4
输出：[2,0,1]

提示：
链表中节点的数目在范围 [0, 500] 内
-100 <= Node.val <= 100
0 <= k <= 2 * 109
"""

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

from DataStructure import LinkedList
from utils import running_time


@running_time
def rotate_right(head: LinkedList.Node, k: int) -> list[int]:
    if head is None:
        return []

    tail = head
    length = 1
    while tail.next is not None:
        length += 1
        tail = tail.next
    tail.next = head
    k = length - (k % length)
    for i in range(k):
        head = head.next
        tail = tail.next
    tail.next = None
    result = []
    for i in range(length):
        result.append(head.data)
        head = head.next
    return result


def test():
    inputs = [
        (LinkedList.from_list([1, 2, 3, 4, 5], LinkedList.Types.NON_HEADER), 2),
        (LinkedList.from_list([0, 1, 2], LinkedList.Types.NON_HEADER), 4),
    ]
    expected = [
        [4, 5, 1, 2, 3],
        [2, 0, 1],
    ]
    for i in range(len(inputs)):
        actual = rotate_right(inputs[i][0].head, inputs[i][1])
        assert expected[i] == actual


def main():
    while True:
        input_str = input("Input the list: ").strip("[]").split(",")
        linked_list = LinkedList.from_list(list(map(int, input_str)), LinkedList.Types.NON_HEADER)
        k = int(input("Input the k: "))
        print(rotate_right(linked_list.head, k), "\n")


if __name__ == "__main__":
    main()
