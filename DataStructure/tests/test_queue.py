"""
The test module for the Queue class.

Queue is a data structure that follows the First In First Out (FIFO) principle.

Here are some considerations for implementing the Queue class:
- Use composition over inheritance for different container backends (e.g., ListContainer, CircularListContainer).
- The Queue class should be configurable via parameters in __init__ rather than requiring subclassing for common
variants.
- Support for bounded queues via max_size parameter.
- Optional support for circular buffer semantics when max_size is set.
- Custom exceptions (QueueEmptyError, QueueOverflowError) should be used instead of built-in ones for clarity.

The Queue class should support the following operations:
- push(item): Add an item to the back of the queue.
- pop(): Remove and return the item from the front of the queue.
- is_empty(): Check if the queue is empty.
- size(): Return the number of items in the queue.
- peek(): Return the item at the front of the queue without removing it.
- clear(): Remove all items from the queue.
- __str__(): Return a string representation of the queue.
- __iter__(): Return an iterator for the queue, allowing users to loop through elements without modifying the queue.
- __eq__() and __ne__(): Compare two queues for equality and inequality based on their contents and order.
- __repr__(): Return a detailed string representation of the queue for debugging purposes.
- __getstate__() and __setstate__(): Support for pickling and unpickling the queue object.

The possible exceptions that could be raised are:
- IndexError: If pop or peek is called on an empty queue.
- ValueError: If push is called with None.
- QueueOverflowError: If the queue exceeds its maximum size (if a max size is defined).
- QueueEmptyError: If pop or peek is called on an empty queue.

The variations of Queue could include:
- Circular Queue: A queue that wraps around when it reaches the end of the underlying data structure
- Deque (Double-Ended Queue): A queue that allows adding and removing items from both ends

The test cases should cover:
- Normal cases for each operation
- Edge cases (e.g., popping from an empty queue)
- Exception handling for invalid operations
- Performance tests for large queues

Test strategy:
- Use pytest.fixture with params to define a parameterized queue fixture that returns instances of different queue
implementations.
- Each test function that accepts queue as a parameter will be run once per implementation.
- This approach is clean, scalable, and leverages pytest's powerful parametrization and test reporting.
- It supports testing both bounded and unbounded variants, different container backends, and future extensions with
minimal code duplication.
"""


class TestQueue:
    def test_queue_is_empty_on_initialization(self, queue_):
        assert queue_.is_empty is True, "New queue should be empty"
        assert queue_.size == 0, "New queue should have size 0"
