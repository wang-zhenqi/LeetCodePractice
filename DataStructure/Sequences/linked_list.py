from typing import Optional


class LinkedList:
    class Node:
        def __init__(self, data: int):
            self.data: int = data
            self.next: Optional["LinkedList.Node"] = None

    head: Optional[Node]

    def __init__(self):
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

    @classmethod
    def instantiate(cls, with_head: bool = False) -> "LinkedList":
        return LinkedListWithHead() if with_head else LinkedListWithoutHead()

    @classmethod
    def from_list(cls, data: list[int], with_head: bool = False) -> "LinkedList":
        linked_list = cls.instantiate(with_head)
        for item in data:
            linked_list.insert(linked_list.length, item)
        return linked_list

    def insert(self, position: int, data: int):
        raise NotImplementedError()

    def delete(self, position: int):
        raise NotImplementedError()

    def find(self, data: int) -> Optional[int]:
        raise NotImplementedError()

    def clear(self):
        self.head = None
        self.length = 0


class LinkedListWithoutHead(LinkedList):
    def __init__(self):
        super().__init__()
        self.head = None

    def insert(self, position: int, data: int):
        if not 0 <= position < self.length and position != self.length:
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
        if not 0 <= position < self.length:
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
        if not 0 <= position < self.length:
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

    def append(self, data: int):
        self.insert(self.length, data)

    def pop(self):
        self.delete(self.length - 1)


class LinkedListWithHead(LinkedList):
    def __init__(self):
        super().__init__()
        self.head = LinkedList.Node(0)

    def insert(self, position: int, data: int):
        if not 0 <= position < self.length and position != self.length:
            raise ValueError("Invalid position")

        new_node = LinkedList.Node(data)
        current = self.head
        for _ in range(position):
            current = current.next
        new_node.next = current.next
        current.next = new_node
        self.length += 1

    def delete(self, position: int):
        if not 0 <= position < self.length:
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
        if not 0 <= index < self.length:
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

    def append(self, data: int):
        self.insert(self.length, data)

    def pop(self):
        self.delete(self.length - 1)
