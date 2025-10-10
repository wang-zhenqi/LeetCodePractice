from abc import ABC, abstractmethod
from typing import List


class ListNode:
    def __init__(self, value: int):
        self.value = value
        self.next: ListNode | None = None


class BidirectionalListNode:
    def __init__(self, value: int):
        self.value = value
        self.next: BidirectionalListNode | None = None
        self.prev: BidirectionalListNode | None = None


class Container(ABC):
    CONTAINER_TYPE: str

    @abstractmethod
    def __init__(self, _: int | None = None): ...

    @property
    @abstractmethod
    def size(self) -> int: ...

    @property
    @abstractmethod
    def is_empty(self) -> bool: ...

    @property
    @abstractmethod
    def is_full(self) -> bool: ...

    @abstractmethod
    def cleanup(self): ...

    @abstractmethod
    def _insert(self, element: int, position: int): ...

    def insert(self, element: int, position: int):
        if element is None or not isinstance(element, int):
            raise TypeError("Only integers are allowed to be added to the queue")
        if self.is_full:
            raise IndexError(f"{self.CONTAINER_TYPE} is full, insertion not allowed")
        self._insert(element, position)

    @abstractmethod
    def _pop(self, position: int) -> int: ...

    def pop(self, position: int) -> int:
        if self.is_empty:
            raise IndexError(f"Popping from empty container `{self.CONTAINER_TYPE}` is not allowed")
        return self._pop(0)

    @abstractmethod
    def _peek(self, position: int) -> int: ...

    def peek(self, position: int) -> int:
        if self.is_empty:
            raise IndexError(f"Peeking from empty container `{self.CONTAINER_TYPE}` is not allowed")
        return self._peek(0)


class DynamicArray(Container):
    CONTAINER_TYPE = "DynamicArray"

    def __init__(self):
        self._list: List[int] = []

    def __str__(self):
        return ", ".join(str(item) for item in self._list)

    @property
    def size(self) -> int:
        return len(self._list)

    @property
    def is_empty(self) -> bool:
        return self.size == 0

    @property
    def is_full(self) -> bool:
        return False

    def cleanup(self):
        self._list.clear()

    def _insert(self, item: int, position: int):
        if position != -1:
            raise NotImplementedError(
                "Insertion at specific position other than the end is not supported for DynamicArray"
            )
        self._list.append(item)

    def _pop(self, position: int) -> int:
        return self._list.pop(0)

    def _peek(self, position) -> int:
        return self._list[0]


class FixedArray(Container):
    CONTAINER_TYPE = "FixedArray"

    def __init__(self, capacity: int):
        self.capacity: int = capacity
        self._storage: List[int | None] = [None] * capacity

    def __str__(self):
        return ", ".join(str(item) for item in self._storage if item is not None)

    @property
    def size(self) -> int:
        return len([item for item in self._storage if item is not None])

    @property
    def is_empty(self) -> bool:
        return self.size == 0

    @property
    def is_full(self) -> bool:
        return self.size == self.capacity

    def cleanup(self):
        self._storage.clear()

    def _insert(self, item: int, position: int):
        if position != -1:
            raise NotImplementedError(
                f"Insertion at specific position ({position}) other than the end is not supported for "
                f"{self.CONTAINER_TYPE}"
            )
        if self.is_full:
            raise IndexError(f"{self.CONTAINER_TYPE} is full, insertion not allowed")
        self._storage[self.size] = item

    def _pop(self, position) -> int:
        return self._storage.pop(0)

    def _peek(self, position) -> int:
        return self._storage[0]


class CircularBuffer(Container):
    CONTAINER_TYPE = "CircularBuffer"

    def __init__(self, capacity: int):
        self.capacity: int = capacity
        self._storage: List[int | None] = [None] * capacity
        self._head: int = 0
        self._tail: int = 0
        self._count: int = 0

    def __str__(self):
        if self.is_empty:
            return ""
        items: List[str] = []
        index: int = self._head
        for _ in range(self.size):
            if self._storage[index] is None:
                break
            items.append(str(self._storage[index]))
            index = (index + 1) % self.capacity
        return ", ".join(items)

    @property
    def size(self) -> int:
        return self._count

    @property
    def is_empty(self) -> bool:
        return self.size == 0

    @property
    def is_full(self) -> bool:
        return self.size == self.capacity

    def cleanup(self):
        self._storage = [None] * self.capacity
        self._head = 0
        self._tail = 0
        self._count = 0

    def _insert(self, element: int, position: int):
        if position != -1:
            raise NotImplementedError(
                f"Insertion at specific position ({position}) other than the end is not supported for "
                f"{self.CONTAINER_TYPE}"
            )
        if self.is_full:
            raise IndexError(f"{self.CONTAINER_TYPE} is full, insertion not allowed")
        self._storage[self._tail] = element
        self._tail = (self._tail + 1) % self.capacity
        self._count += 1

    def _pop(self, position) -> int:
        element = self._storage[self._head]
        self._storage[self._head] = None
        self._head = (self._head + 1) % self.capacity
        self._count -= 1
        return element

    def _peek(self, position) -> int:
        return self._storage[self._head]


class DoubleEndedDynamicArray(Container):
    CONTAINER_TYPE = "DoubleEndedDynamicArray"

    def __init__(self):
        self._storage: List[int] = []
        self.head: int = 0
        self.tail: int = -1

    def __str__(self):
        return ", ".join(str(item) for item in self._storage)

    @property
    def size(self) -> int:
        return len(self._storage)

    @property
    def is_empty(self) -> bool:
        return self.size == 0

    @property
    def is_full(self) -> bool:
        return False

    def cleanup(self):
        self._storage.clear()
        self.head = 0
        self.tail = -1

    def _insert(self, item: int, position: int):
        if position == 0:
            self._storage.insert(0, item)
        elif position == -1:
            self._storage.insert(self.size, item)
        else:
            raise NotImplementedError(
                f"Insertion at specific position ({position}) other than the both ends is not allowed."
            )

    def _pop(self, position) -> int:
        if position == 0:
            return self._storage.pop(0)
        elif position == -1:
            return self._storage.pop(self.size - 1)
        else:
            raise NotImplementedError(
                f"Popping from a specific position ({position}) other than the both ends is not allowed."
            )

    def _peek(self, position) -> int:
        if position == 0:
            return self._storage[0]
        elif position == -1:
            return self._storage[self.size - 1]
        else:
            raise NotImplementedError(
                f"Peeking from a specific position ({position}) other than the both ends is not allowed."
            )


class DoubleEndedFixedArray(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class LinkedList(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class BoundedLinkedList(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class CircularLinkedList(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class DoubleEndedLinkedList(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class BoundedDoubleEndedLinkedList(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class Deque(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class BoundedDeque(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class CircularDeque(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class DoubleEndedDeque(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass


class BoundedDoubleEndedDeque(Container):
    def __init__(self):
        pass

    @property
    def size(self) -> int:
        pass

    @property
    def is_empty(self) -> bool:
        pass

    def cleanup(self):
        pass

    def insert(self, element):
        pass

    def pop(self, position) -> int:
        pass

    def peek(self, position) -> int:
        pass
