"""
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]

提示：
链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
"""

from typing import Optional

import pytest

from utils import running_time


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    @running_time
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        # 考虑到倒数最后一个节点（正数第一个），使用一个虚拟头节点会让情况变得统一
        dummy_head = ListNode(val=-1, next=head)
        ptr1 = ptr2 = dummy_head

        # 让 ptr2 先走 n + 1 步，这样 ptr1 和 ptr2 的距离就是 n + 1。
        while n >= 0:
            ptr2 = ptr2.next
            n -= 1

        # 然后再让 ptr1 和 ptr2 同步向后走，当 ptr2 到达末尾（None）时，ptr1 正好在倒数第 n+1 个节点处
        while ptr2:
            ptr1 = ptr1.next
            ptr2 = ptr2.next

        # 跨过 ptr1 的下一位，就相当于删除了倒数第 n 个节点
        ptr1.next = ptr1.next.next

        # 虚拟头节点的下一个节点才是真正的头节点
        return dummy_head.next


@pytest.mark.parametrize(
    "input_list,n,expected",
    [
        (
            [1, 2, 3, 4, 5],
            2,
            "[1,2,3,5]",
        ),
        (
            [1],
            1,
            "[]",
        ),
        (
            [1, 2],
            1,
            "[1]",
        ),
        (
            [1, 2],
            2,
            "[2]",
        ),
    ],
)
def test_remove_nth_from_end(input_list, n, expected):
    solution = Solution()
    head = generate_linked_list(input_list)
    new_head = solution.removeNthFromEnd(head, n)
    assert stringify_linked_list(new_head) == expected


def generate_linked_list(input_list: list[int]) -> Optional[ListNode]:
    if len(input_list) == 0 or input_list is None:
        return None
    head = ListNode(val=input_list[0], next=None)
    current = head
    for i in range(1, len(input_list)):
        new_node = ListNode(val=input_list[i], next=None)
        current.next = new_node
        current = new_node
    return head


@pytest.fixture()
def test_input():
    return [1, 3, 5, 7, 9]


def test_given_a_list_of_int_should_return_a_head_of_a_linked_list(test_input):
    head = generate_linked_list(test_input)
    assert head.val == 1
    assert head.next.val == 3

    length = 0
    while head:
        length += 1
        head = head.next

    assert length == 5

    head = generate_linked_list([1])
    assert head.val == 1
    assert head.next is None


def test_given_an_empty_list_should_return_a_none_head():
    head = generate_linked_list([])
    assert head is None


def stringify_linked_list(head: Optional[ListNode]) -> str:
    result = []
    while head:
        result.append(str(head.val))
        head = head.next
    return "[" + ",".join(result) + "]"


def test_given_a_head_should_return_a_string_to_represent_the_linked_list(test_input):
    head = generate_linked_list(test_input)
    assert "[1,3,5,7,9]" == stringify_linked_list(head)


def test_given_a_none_head_should_return_a_pair_of_square_bracket():
    assert "[]" == stringify_linked_list(None)


def main():
    while True:
        in_list = eval(input())
        n = int(input())
        linked_list = generate_linked_list(in_list)
        result = Solution().removeNthFromEnd(linked_list, n)
        print(stringify_linked_list(result))


if __name__ == "__main__":
    main()
