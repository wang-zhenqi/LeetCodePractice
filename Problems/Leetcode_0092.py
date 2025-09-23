"""
反转链表2 - 部分反转

给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。


示例 1：
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]

示例 2：
输入：head = [5], left = 1, right = 1
输出：[5]


提示：

链表中节点数目为 n
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n


进阶： 你可以使用一趟扫描完成反转吗？
"""
from typing import Optional

import pytest


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def create_linked_list(values: list[int]) -> Optional[ListNode]:
    dummy_head = ListNode(-1)
    p = dummy_head

    for v in values:
        p.next = ListNode(v)
        p = p.next

    return dummy_head.next


def list_values(head: Optional[ListNode]) -> list[int]:
    result = []

    while head:
        result.append(head.val)
        head = head.next

    return result


def _reverse(head: ListNode, tail: ListNode):
    if not head.next:
        print(list_values(head))
        return head, head

    t = head
    new_head, new_tail = _reverse(head.next, tail)
    new_tail.next = t
    t.next = None

    print(list_values(new_head))
    return new_head, t


class Solution:
    def reverseBetween(self, head: Optional[ListNode], left: int, right: int) -> Optional[ListNode]:
        if left == right:
            return head

        dummy_head = ListNode(-1, head)

        node_before = dummy_head
        partial_head = partial_tail = dummy_head

        step = right - left
        while step:
            partial_tail = partial_tail.next
            step -= 1

        partial_head = partial_head.next
        partial_tail = partial_tail.next
        left -= 1
        while left:
            node_before = node_before.next
            partial_head = partial_head.next
            partial_tail = partial_tail.next
            left -= 1

        node_after = partial_tail.next
        partial_tail.next = None

        new_partial_head, new_partial_tail = _reverse(partial_head, partial_tail)

        node_before.next = new_partial_head
        new_partial_tail.next = node_after

        return dummy_head.next


def test_create_linked_list_should_return_the_head_when_given_list_of_values():
    values = []
    assert create_linked_list(values) is None

    values = [1, 2, 3]
    head = create_linked_list(values)
    assert head.val == 1
    assert head.next is not None
    assert head.next.val == 2
    assert head.next.next is not None
    assert head.next.next.val == 3
    assert head.next.next.next is None


def test_list_values_should_return_list_of_values_in_the_linked_list():
    tail = ListNode(1)
    middle = ListNode(2, tail)
    head = ListNode(3, middle)

    assert list_values(head) == [3, 2, 1]

    head = ListNode(5)
    assert list_values(head) == [5]

    assert list_values(None) == []


@pytest.mark.parametrize(
    "values, left, right, expected",
    [
        ([5], 1, 1, [5]),
        ([4, 5, 6], 2, 2, [4, 5, 6]),
    ],
)
def test_reverse_between_should_return_the_same_linked_list_when_left_equals_to_right(values, left, right, expected):
    head = create_linked_list(values)
    result = Solution().reverseBetween(head, left, right)
    actual = list_values(result)
    assert actual == expected


@pytest.mark.parametrize(
    "values, left, right, expected",
    [
        ([1, 2, 3, 4, 5], 2, 4, [1, 4, 3, 2, 5]),
        ([1, 2, 3, 4], 1, 4, [4, 3, 2, 1]),
        ([6, 7, 8, 9], 1, 2, [7, 6, 8, 9]),
    ],
)
def test_reverse_between_should_return_the_partially_reversed_linked_list_when_left_less_than_right(
    values, left, right, expected
):
    head = create_linked_list(values)
    result = Solution().reverseBetween(head, left, right)
    actual = list_values(result)
    assert actual == expected
