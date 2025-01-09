"""
环形链表
"""

from DataStructure import LinkedList
from utils import running_time


@running_time
def judge(ll: LinkedList) -> bool:
    if ll.head is None:
        return False

    slow = fast = ll.head
    while fast.next is not None and fast.next.next is not None:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return True

    return False


if __name__ == "__main__":
    linked_list1 = LinkedList.from_list([10, 20, 30, 40, 50], list_type=LinkedList.Types.CIRCULAR, circular_position=1)
    linked_list2 = LinkedList.from_list([10, 20, 30, 40, 50], list_type=LinkedList.Types.NON_HEADER)

    print("Linked list 1: " + str(linked_list1))
    print("Linked list 2: " + str(linked_list2))

    print("Linked list 1 is circular: " + str(judge(linked_list1)))
    print("Linked list 2 is circular: " + str(judge(linked_list2)))
