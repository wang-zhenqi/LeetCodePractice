import pytest

from DataStructure.queues import ContainerConfig, Queue


@pytest.fixture(
    params=[
        lambda: Queue(config=ContainerConfig()),
        lambda: Queue(config=ContainerConfig(max_size=10)),
        lambda: Queue(config=ContainerConfig(max_size=9, variation="circular")),
        lambda: Queue(config=ContainerConfig(variation="double_ended")),
        lambda: Queue(config=ContainerConfig(max_size=8, variation="double_ended")),
        lambda: Queue(config=ContainerConfig(implementation="linked")),
        lambda: Queue(config=ContainerConfig(max_size=7, implementation="linked")),
        lambda: Queue(config=ContainerConfig(max_size=6, implementation="linked", variation="circular")),
        lambda: Queue(config=ContainerConfig(implementation="linked", variation="double_ended")),
        lambda: Queue(config=ContainerConfig(max_size=5, implementation="linked", variation="double_ended")),
        lambda: Queue(config=ContainerConfig(implementation="deque")),
        lambda: Queue(config=ContainerConfig(max_size=4, implementation="deque")),
        lambda: Queue(config=ContainerConfig(max_size=3, implementation="deque", variation="circular")),
        lambda: Queue(config=ContainerConfig(implementation="deque", variation="double_ended")),
        lambda: Queue(config=ContainerConfig(max_size=10, implementation="deque", variation="double_ended")),
    ],
    ids=[
        "DynamicArray",
        "FixedArray",
        "CircularBuffer",
        "DoubleEndedDynamicArray",
        "DoubleEndedFixedArray",
        "LinkedList",
        "BoundedLinkedList",
        "CircularLinkedList",
        "DoubleEndedLinkedList",
        "BoundedDoubleEndedLinkedList",
        "Deque",
        "BoundedDeque",
        "CircularDeque",
        "DoubleEndedDeque",
        "BoundedDoubleEndedDeque",
    ],
    scope="module",
)
def queue_(request):
    return request.param()
