from typing import Optional

import pytest


class LinkedList:
    class Node:
        def __init__(self, data: int):
            self.data: int = data
            self.next: Optional["LinkedList.Node"] = None

    def __init__(self):
        self.head: Optional[LinkedList.Node] = None
        self.length = 0

    def __getitem__(self, position: int) -> Optional[int]:
        raise NotImplementedError()

    def __repr__(self):
        raise NotImplementedError()

    def __str__(self):
        raise NotImplementedError()

    @staticmethod
    def _locate_element_from_node(current: Node, data: int) -> Optional[int]:
        position = 0
        while current is not None:
            if current.data == data:
                return position
            current = current.next
            position += 1
        return None

    def _position_is_valid(self, position: int):
        return 0 <= position < self.length

    @classmethod
    def instantiate(cls, with_head: bool = False) -> "LinkedList":
        return LinkedListWithHead() if with_head else LinkedListWithoutHead()

    @classmethod
    def from_list(cls, data: list[int], with_head: bool = False) -> "LinkedList":
        linked_list = cls.instantiate(with_head)
        for item in data:
            linked_list.append(item)
        return linked_list

    def insert(self, position: int, data: int):
        raise NotImplementedError()

    def append(self, data: int):
        self.insert(self.length, data)

    def delete(self, position: int):
        raise NotImplementedError()

    def pop(self):
        self.delete(self.length - 1)

    def find(self, data: int) -> Optional[int]:
        raise NotImplementedError()


class LinkedListWithoutHead(LinkedList):
    def insert(self, position: int, data: int):
        if not self._position_is_valid(position) and position != self.length:
            raise ValueError("Invalid position")

        new_node = LinkedList.Node(data)
        if position == 0:
            new_node.next = self.head
            self.head = new_node
        else:
            current = self.head
            for _ in range(position - 1):
                current = current.next
            new_node.next = current.next
            current.next = new_node
        self.length += 1

    def delete(self, position: int):
        if not self._position_is_valid(position):
            raise ValueError("Invalid position")

        if position == 0:
            self.head = self.head.next
        else:
            current = self.head
            for _ in range(1, position):
                current = current.next
            current.next = current.next.next
        self.length -= 1

    def find(self, data: int) -> Optional[int]:
        current = self.head
        return LinkedList._locate_element_from_node(current, data)

    def __getitem__(self, position: int) -> Optional[int]:
        if not self._position_is_valid(position):
            raise ValueError("Invalid position")
        current = self.head
        for _ in range(position):
            current = current.next
        return current.data

    def __str__(self):
        current = self.head
        result = []
        while current is not None:
            result.append(str(current.data))
            current = current.next
        return " -> ".join(result)

    def __repr__(self):
        # "    0     1     2     3     4"
        # "   10 -> 20 -> 30 -> 40 -> 50"
        return (
            " ".join([str(i).rjust(5) for i in range(self.length)])
            + "\n"
            + " " * (5 - len(str(self.head.data)))
            + str(self)
        )


class LinkedListWithHead(LinkedList):
    def __init__(self):
        super().__init__()
        self.head = LinkedList.Node(0)

    def insert(self, position: int, data: int):
        if not self._position_is_valid(position) and position != self.length:
            raise ValueError("Invalid position")

        new_node = LinkedList.Node(data)
        current = self.head
        for _ in range(position):
            current = current.next
        new_node.next = current.next
        current.next = new_node
        self.length += 1

    def delete(self, position: int):
        if not self._position_is_valid(position):
            raise ValueError("Invalid position")

        current = self.head
        for _ in range(position):
            current = current.next
        current.next = current.next.next
        self.length -= 1

    def find(self, data: int) -> Optional[int]:
        current = self.head.next
        return LinkedList._locate_element_from_node(current, data)

    def __getitem__(self, index: int) -> Optional[int]:
        if not self._position_is_valid(index):
            raise ValueError("Invalid position")
        current = self.head.next
        for _ in range(index):
            current = current.next
        return current.data

    def __str__(self):
        current = self.head.next
        result = []
        while current is not None:
            result.append(str(current.data))
            current = current.next
        return " -> ".join(result)

    def __repr__(self):
        # "         0     1     2     3     4"
        # "head -> 10 -> 20 -> 30 -> 40 -> 50"
        return (
            " " * len("head ") + " ".join([str(i).rjust(5) for i in range(self.length)]) + "\n" + "head -> " + str(self)
        )


class TestLinkedList:
    def test_create_node_should_generate_a_node_with_input_value_and_null_next(self):
        node = LinkedList.Node(1)
        assert node.data == 1
        assert node.next is None

    def test_create_linked_list_should_generate_a_linked_list_with_proper_head(self):
        linked_list_with_head = LinkedList.instantiate(True)
        assert linked_list_with_head.head is not None
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next is None
        assert linked_list_with_head.length == 0

        linked_list_without_head = LinkedList.instantiate(False)
        assert linked_list_without_head.head is None
        assert linked_list_without_head.length == 0

    def test_insert_should_insert_a_node_to_linked_list_at_position_0(self):
        linked_list_with_head = LinkedList.instantiate(True)
        linked_list_with_head.insert(0, 10)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.length == 1

        linked_list_without_head = LinkedList.instantiate(False)
        linked_list_without_head.insert(0, 10)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.length == 1

    def test_insert_should_insert_a_node_to_linked_list_at_last(self):
        linked_list_with_head = LinkedList.instantiate(True)
        linked_list_with_head.insert(0, 10)
        linked_list_with_head.insert(1, 20)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.head.next.next.data == 20
        assert linked_list_with_head.length == 2

        linked_list_without_head = LinkedList.instantiate(False)
        linked_list_without_head.insert(0, 10)
        linked_list_without_head.insert(1, 20)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.head.next.data == 20
        assert linked_list_without_head.length == 2

    def test_insert_should_raise_exception_when_position_is_invalid(self):
        linked_list_with_head = LinkedList.instantiate(True)
        with pytest.raises(ValueError) as e:
            linked_list_with_head.insert(2, 10)
            assert str(e.value) == "Invalid position"

        linked_list_without_head = LinkedList.instantiate(False)
        with pytest.raises(ValueError) as e:
            linked_list_without_head.insert(2, 10)
            assert str(e.value) == "Invalid position"

    def test_delete_should_delete_a_node_from_linked_list_at_position_0(self):
        linked_list_with_head = LinkedList.from_list([10, 20], True)
        linked_list_with_head.delete(0)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 20
        assert linked_list_with_head.length == 1

        linked_list_without_head = LinkedList.from_list([10, 20], False)
        linked_list_without_head.delete(0)
        assert linked_list_without_head.head.data == 20
        assert linked_list_without_head.length == 1

    def test_delete_should_delete_a_node_from_linked_list_at_position(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], True)
        linked_list_with_head.delete(1)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.head.next.next.data == 30
        assert linked_list_with_head.length == 2

        linked_list_without_head = LinkedList.from_list([10, 20, 30], False)
        linked_list_without_head.delete(1)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.head.next.data == 30
        assert linked_list_without_head.length == 2

    def test_delete_should_raise_exception_when_position_is_invalid(self):
        linked_list_with_head = LinkedList.from_list([10, 20], True)
        with pytest.raises(ValueError) as e:
            linked_list_with_head.delete(2)
            assert str(e.value) == "Invalid position"

        linked_list_without_head = LinkedList.from_list([10, 20], False)
        with pytest.raises(ValueError) as e:
            linked_list_without_head.delete(2)
            assert str(e.value) == "Invalid position"

    def test_pop_should_delete_the_last_node_from_linked_list(self):
        linked_list_with_head = LinkedList.from_list([10, 20], True)
        linked_list_with_head.pop()
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.length == 1

        linked_list_without_head = LinkedList.from_list([10, 20], False)
        linked_list_without_head.pop()
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.length == 1

    def test_append_should_insert_a_node_to_linked_list_at_last(self):
        linked_list_with_head = LinkedList.from_list([10], True)
        linked_list_with_head.append(20)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.head.next.next.data == 20
        assert linked_list_with_head.length == 2

        linked_list_without_head = LinkedList.from_list([10], False)
        linked_list_without_head.append(20)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.head.next.data == 20
        assert linked_list_without_head.length == 2

    def test_str_should_return_string_representation_of_linked_list(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], True)
        assert str(linked_list_with_head) == "10 -> 20 -> 30"

        linked_list_without_head = LinkedList.from_list([10, 20, 30], False)
        assert str(linked_list_without_head) == "10 -> 20 -> 30"

    def test_find_should_return_position_of_data_in_linked_list(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], True)
        assert linked_list_with_head.find(20) == 1
        assert linked_list_with_head.find(200) is None

        linked_list_without_head = LinkedList.from_list([10, 20, 30], False)
        assert linked_list_without_head.find(30) == 2
        assert linked_list_without_head.find(200) is None

    def test_getitem_should_return_data_at_position(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], True)
        assert linked_list_with_head[1] == 20

        linked_list_without_head = LinkedList.from_list([10, 20, 30], False)
        assert linked_list_without_head[2] == 30

    def test_getitem_should_raise_exception_when_position_is_invalid(self):
        linked_list_with_head = LinkedList.from_list([10, 20], True)
        with pytest.raises(ValueError) as e:
            _ = linked_list_with_head[2]
            assert str(e.value) == "Invalid position"

        linked_list_without_head = LinkedList.from_list([10, 20], False)
        with pytest.raises(ValueError) as e:
            _ = linked_list_without_head[2]
            assert str(e.value) == "Invalid position"


if __name__ == "__main__":
    headed_ll = LinkedList.from_list([10, 20, 30], True)
    print(headed_ll.__repr__())
    non_headed_ll = LinkedList.from_list([10, 20, 30], False)
    print(non_headed_ll.__repr__())
