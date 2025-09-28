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
    @abstractmethod
    def __init__(self, _: int | None = None):
        ...

    @property
    @abstractmethod
    def size(self) -> int:
        ...

    @property
    @abstractmethod
    def is_empty(self) -> bool:
        ...

    @abstractmethod
    def cleanup(self):
        ...

    @abstractmethod
    def append(self, element):
        ...

    @abstractmethod
    def pop(self) -> int:
        ...

    @abstractmethod
    def peek(self) -> int:
        ...


class DynamicArray(Container):
    def __init__(self):
        self._storage: List[int] = []

    @property
    def size(self) -> int:
        return len(self._storage)

    @property
    def is_empty(self) -> bool:
        return self.size == 0

    def cleanup(self):
        self._storage.clear()

    def append(self, item: int):
        self._storage.append(item)

    def pop(self) -> int:
        if self.is_empty:
            raise IndexError("pop from empty queue")
        return self._storage.pop(0)

    def peek(self) -> int:
        if self.is_empty:
            raise IndexError("peek from empty queue")
        return self._storage[0]


class FixedArray(Container):
    def __init__(self, max_size: int):
        self._max_size = max_size
        self._storage: List[int | None] = [None] * max_size

    @property
    def size(self) -> int:
        return len([item for item in self._storage if item is not None])

    @property
    def is_empty(self) -> bool:
        return self.size == 0

    def cleanup(self):
        self._storage.clear()

    def append(self, item: int):
        if self.size >= self._max_size:
            raise OverflowError("Queue is full")
        self._storage.append(item)

    def pop(self) -> int:
        if self.is_empty:
            raise IndexError("pop from empty queue")
        return self._storage.pop(0)

    def peek(self) -> int:
        if self.is_empty:
            raise IndexError("peek from empty queue")
        return self._storage[0]


class CircularBuffer(Container):
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
        pass


class DoubleEndedDynamicArray(Container):
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
        pass


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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
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

    def append(self, element):
        pass

    def pop(self) -> int:
        pass

    def peek(self) -> int:
        pass
