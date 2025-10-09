import pytest

from DataStructure.queues import ContainerConfig, Queue

bounded_queues = (
    [
        lambda: Queue(config=ContainerConfig(max_size=10)),
        lambda: Queue(config=ContainerConfig(max_size=9, variation="circular")),
        # lambda: Queue(config=ContainerConfig(max_size=8, variation="double_ended")),
        # lambda: Queue(config=ContainerConfig(max_size=7, implementation="linked")),
        # lambda: Queue(config=ContainerConfig(max_size=6, implementation="linked", variation="circular")),
        # lambda: Queue(config=ContainerConfig(max_size=5, implementation="linked", variation="double_ended")),
        # lambda: Queue(config=ContainerConfig(max_size=4, implementation="deque")),
        # lambda: Queue(config=ContainerConfig(max_size=3, implementation="deque", variation="circular")),
        # lambda: Queue(config=ContainerConfig(max_size=10, implementation="deque", variation="double_ended")),
    ],
    [
        "FixedArray",
        "CircularBuffer",
        # "DoubleEndedFixedArray",
        # "BoundedLinkedList",
        # "CircularLinkedList",
        # "BoundedDoubleEndedLinkedList",
        # "BoundedDeque",
        # "CircularDeque",
        # "BoundedDoubleEndedDeque",
    ],
)

unbounded_queues = (
    [
        lambda: Queue(config=ContainerConfig()),
        # lambda: Queue(config=ContainerConfig(variation="double_ended")),
        # lambda: Queue(config=ContainerConfig(implementation="linked")),
        # lambda: Queue(config=ContainerConfig(implementation="linked", variation="double_ended")),
        # lambda: Queue(config=ContainerConfig(implementation="deque")),
        # lambda: Queue(config=ContainerConfig(implementation="deque", variation="double_ended")),
    ],
    [
        "DynamicArray",
        # "DoubleEndedDynamicArray",
        # "LinkedList",
        # "DoubleEndedLinkedList",
        # "Deque",
        # "DoubleEndedDeque",
    ],
)


@pytest.fixture(
    params=bounded_queues[0] + unbounded_queues[0],
    ids=bounded_queues[1] + unbounded_queues[1],
    scope="function",
)
def general_queue(request):
    return request.param()


@pytest.fixture(params=bounded_queues[0], ids=bounded_queues[1], scope="function")
def bounded_queue(request):
    return request.param()


@pytest.fixture(params=unbounded_queues[0], ids=unbounded_queues[1], scope="function")
def unbounded_queue(request):
    return request.param()
