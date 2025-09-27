import pytest

from DataStructure.queues import Queue


@pytest.fixture(
    params=[
        pytest.param(
            lambda: Queue(
                max_size=5,
                implementation="sequential",
            ),
            id="Bounded_Queue_by_Sequential",
        ),
        pytest.param(
            lambda: Queue(
                max_size=5,
                implementation="linked",
            ),
            id="Bounded_Queue_by_LinkedList",
        ),
        pytest.param(
            lambda: Queue(
                max_size=5,
                implementation="deque",
            ),
            id="Bounded_Queue_by_Deque",
        ),
        pytest.param(
            lambda: Queue(
                implementation="sequential",
            ),
            id="Unbounded_Queue_by_Sequential",
        ),
        pytest.param(
            lambda: Queue(
                implementation="linked",
            ),
            id="Unbounded_Queue_by_LinkedList",
        ),
        pytest.param(
            lambda: Queue(
                implementation="deque",
            ),
            id="Unbounded_Queue_by_Deque",
        ),
        pytest.param(
            lambda: Queue(max_size=5, implementation="sequential", variation="circular"),
            id="CircularQueue_by_Sequential",
        ),
        pytest.param(
            lambda: Queue(max_size=5, implementation="linked", variation="circular"), id="CircularQueue_by_LinkedList"
        ),
        pytest.param(
            lambda: Queue(max_size=5, implementation="deque", variation="circular"), id="CircularQueue_by_Deque"
        ),
        pytest.param(
            lambda: Queue(max_size=5, implementation="sequential", variation="deque"), id="Bounded_Deque_by_Sequential"
        ),
        pytest.param(
            lambda: Queue(max_size=5, implementation="linked", variation="deque"), id="Bounded_Deque_by_LinkedList"
        ),
        pytest.param(lambda: Queue(max_size=5, implementation="deque", variation="deque"), id="Bounded_Deque_by_Deque"),
        pytest.param(lambda: Queue(implementation="sequential", variation="deque"), id="Unbounded_Deque_by_Sequential"),
        pytest.param(lambda: Queue(implementation="linked", variation="deque"), id="Unbounded_Deque_by_LinkedList"),
        pytest.param(lambda: Queue(implementation="deque", variation="deque"), id="Unbounded_Deque_by_Deque"),
    ],
    scope="package",
)
def queue_(request):
    factory = request.param
    return factory()
