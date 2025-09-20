from typing import Optional

import pytest


class ListNode:
    def __init__(self, x: int):
        self.val: int = x
        self.next: Optional["ListNode"] = None


def form_linked_list(values: list[int], pos: int) -> ListNode:
    dummy_head: ListNode = ListNode(-1)

    p: ListNode = dummy_head
    tail = dummy_head

    for v in values:
        p.next = ListNode(v)
        p = p.next
        if pos >= 0:
            tail = tail.next
            pos -= 1

    p.next = tail if tail is not dummy_head else None
    return dummy_head.next


class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return None

        dummy_head = ListNode(-1)
        dummy_head.next = head

        slow = dummy_head.next
        fast = dummy_head.next.next

        while slow is not fast and fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        if slow is not fast:
            return None

        slow = dummy_head
        while slow is not fast:
            slow = slow.next
            fast = fast.next

        return slow


@pytest.mark.parametrize(
    "values, pos, expected",
    [
        ([3, 2, 0, -4], 1, 1),
        ([1, 2], 0, 0),
        ([1], -1, None),
    ],
)
def test_solution(values, pos, expected):
    head_node = form_linked_list(values, pos)

    result: Optional[ListNode] = Solution().detectCycle(head_node)

    if not result:
        actual = None
    else:
        try:
            actual = values.index(result.val)
        except ValueError:
            actual = None
    print(result)
    print(actual)
    assert actual == expected
