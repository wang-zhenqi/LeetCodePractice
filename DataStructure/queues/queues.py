from DataStructure.queues import ContainerConfig
from DataStructure.queues.exceptions import (
    QueueElementTypeError,
    QueueEmptyError,
    QueueOperationNotSupportedError,
    QueueOverflowError,
)
from DataStructure.queues.factory import create_container


class Queue:
    def __init__(self, config: ContainerConfig = ContainerConfig()):
        self.container = create_container(config)

    def __str__(self):
        return str(self.container)

    @property
    def count(self) -> int:
        return self.container.count

    @property
    def is_empty(self) -> bool:
        return self.container.is_empty

    def push(self, item):
        try:
            self.container.insert(item, -1)
        except TypeError as e:
            raise QueueElementTypeError("Only non-None integers are allowed") from e
        except IndexError as e:
            raise QueueOverflowError("No space in queue for pushing") from e

    def pop(self):
        try:
            return self.container.pop(0)
        except IndexError as e:
            raise QueueEmptyError("Queue is empty, cannot pop") from e

    def peek(self):
        try:
            return self.container.peek(0)
        except IndexError as e:
            raise QueueEmptyError("Queue is empty, cannot peek") from e

    def push_left(self, item):
        try:
            self.container.insert(item, 0)
        except NotImplementedError as e:
            raise QueueOperationNotSupportedError("`push_left` is not supported for this queue implementation") from e

    def pop_right(self):
        try:
            return self.container.pop(-1)
        except NotImplementedError as e:
            raise QueueOperationNotSupportedError("`pop_right` is not supported for this queue implementation") from e
        except IndexError as e:
            raise QueueEmptyError("Queue is empty, cannot pop_right") from e

    def peek_right(self):
        try:
            return self.container.peek(-1)
        except NotImplementedError as e:
            raise QueueOperationNotSupportedError("`peek_right` is not supported for this queue implementation") from e
        except IndexError as e:
            raise QueueEmptyError("Queue is empty, cannot peek_right") from e

    def cleanup(self):
        self.container.cleanup()
