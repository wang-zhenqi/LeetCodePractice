import pytest

from DataStructure.queues import ContainerConfig, Queue


@pytest.fixture(
    params=[
        Queue(config=ContainerConfig()),
        Queue(config=ContainerConfig(max_size=10)),
        Queue(config=ContainerConfig(max_size=9, variation="circular")),
        Queue(config=ContainerConfig(variation="double_ended")),
        Queue(config=ContainerConfig(max_size=8, variation="double_ended")),
        Queue(config=ContainerConfig(implementation="linked")),
        Queue(config=ContainerConfig(max_size=7, implementation="linked")),
        Queue(config=ContainerConfig(max_size=6, implementation="linked", variation="circular")),
        Queue(config=ContainerConfig(implementation="linked", variation="double_ended")),
        Queue(config=ContainerConfig(max_size=5, implementation="linked", variation="double_ended")),
        Queue(config=ContainerConfig(implementation="deque")),
        Queue(config=ContainerConfig(max_size=4, implementation="deque")),
        Queue(config=ContainerConfig(max_size=3, implementation="deque", variation="circular")),
        Queue(config=ContainerConfig(implementation="deque", variation="double_ended")),
        Queue(config=ContainerConfig(max_size=10, implementation="deque", variation="double_ended")),
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
    return request.param
