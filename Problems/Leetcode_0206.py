"""
反转链表
"""

from enum import Enum

from DataStructure import LinkedList
from utils import running_time


class ReverseMethod(Enum):
    RECURSIVELY = 1
    SEQUENTIALLY = 2


def recursively(ll):
    if ll.head.next is None:
        return ll
    reversed_tail = recursively(LinkedList.from_list(ll.to_list()[1:], ll.type))
    reversed_tail.append(ll.head.data)
    return reversed_tail


def sequentially(ll: LinkedList) -> LinkedList:
    p = None
    c = ll.head
    n = c.next

    while n is not None:
        c.next = p
        p = c
        c = n
        n = n.next
    c.next = p

    reversed_ll = LinkedList.instantiate(ll.type)

    while c is not None:
        reversed_ll.append(c.data)
        c = c.next

    return reversed_ll


@running_time
def reverse(ll: LinkedList, method: ReverseMethod = ReverseMethod.RECURSIVELY) -> LinkedList:
    if method == ReverseMethod.SEQUENTIALLY:
        return recursively(ll)
    else:
        return sequentially(ll)


if __name__ == "__main__":
    linked_list = LinkedList.from_list([1, 2, 3, 4, 5], list_type=LinkedList.Types.NON_HEADER)
    result = reverse(linked_list, ReverseMethod.SEQUENTIALLY)
    print("Sequentially: \n" + str(result))
    print("======")
    result = reverse(linked_list, ReverseMethod.RECURSIVELY)
    print("Recursively: \n" + str(result))
