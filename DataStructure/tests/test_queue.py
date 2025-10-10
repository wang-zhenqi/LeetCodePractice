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

import pytest

from DataStructure.queues.exceptions import (
    QueueElementTypeError,
    QueueEmptyError,
    QueueOperationNotSupportedError,
    QueueOverflowError,
)


class TestGeneralQueue:
    def test_queue_is_empty_on_initialization(self, general_queue):
        assert general_queue.is_empty is True, "New queue should be empty"
        assert general_queue.count == 0, "New queue should have size 0"

    def test_queue_push_increases_size(self, general_queue):
        general_queue.push(1)
        general_queue.push(2)
        assert general_queue.count == 2, "Queue size should increase after push"
        assert general_queue.is_empty is False, "Queue should not be empty after push"
        assert str(general_queue) == "1, 2", "Queue string representation should not be None"

    def test_queue_pop_decreases_size(self, general_queue):
        general_queue.push(1)
        general_queue.push(2)
        popped = general_queue.pop()
        assert popped == 1, "Popped element should be the first pushed element"
        assert general_queue.count == 1, "Queue size should decrease after pop"
        assert str(general_queue) == "2", "Queue string representation should reflect the current state"

    def test_queue_peek_does_not_change_size(self, general_queue):
        general_queue.push(1)
        general_queue.push(2)
        peeked = general_queue.peek()
        assert peeked == 1, "Peeked element should be the first pushed element"
        assert general_queue.count == 2, "Queue size should not change after peek"
        assert str(general_queue) == "1, 2", "Queue string representation should remain unchanged after peek"

    def test_queue_pop_until_empty(self, general_queue):
        general_queue.push(1)
        general_queue.push(2)
        general_queue.pop()
        general_queue.pop()
        assert general_queue.is_empty is True, "Queue should be empty after popping all elements"
        assert general_queue.count == 0, "Queue size should be 0 after popping all elements"
        assert str(general_queue) == "", "Queue string representation should be empty after popping all elements"

    def test_queue_push_none_raises_exception(self, general_queue):
        with pytest.raises(QueueElementTypeError):
            general_queue.push(None)
        assert general_queue.is_empty is True, "Queue should remain empty after attempting to push None"
        assert general_queue.count == 0, "Queue size should remain 0 after attempting to push None"

    def test_queue_push_non_integer_raises_exception(self, general_queue):
        with pytest.raises(QueueElementTypeError):
            general_queue.push("string")
        with pytest.raises(QueueElementTypeError):
            general_queue.push(3.14)
        with pytest.raises(QueueElementTypeError):
            general_queue.push([1, 2, 3])
        assert general_queue.is_empty is True, "Queue should remain empty after attempting to push non-integer"
        assert general_queue.count == 0, "Queue size should remain 0 after attempting to push non-integer"

    def test_queue_pop_from_empty_raises_exception(self, general_queue):
        with pytest.raises(QueueEmptyError):
            general_queue.pop()
        assert general_queue.is_empty is True, "Queue should remain empty after attempting to pop from empty"
        assert general_queue.count == 0, "Queue size should remain 0 after attempting to pop from empty"

    def test_queue_peek_from_empty_raises_exception(self, general_queue):
        with pytest.raises(QueueEmptyError):
            general_queue.peek()
        assert general_queue.is_empty is True, "Queue should remain empty after attempting to peek from empty"
        assert general_queue.count == 0, "Queue size should remain 0 after attempting to peek from empty"


class TestBoundedQueue:
    def test_bounded_queue_capacity_should_not_change_when_popped(self, bounded_queue):
        capacity = bounded_queue.container.capacity
        bounded_queue.push(1)
        bounded_queue.pop()
        assert bounded_queue.container.capacity == capacity
        assert bounded_queue.count == 0

    def test_bounded_queue_push_until_full(self, bounded_queue):
        capacity = bounded_queue.container.capacity
        for i in range(capacity):
            bounded_queue.push(i)
        assert bounded_queue.count == capacity, "Bounded queue should reach max size after pushing max_size elements"
        with pytest.raises(QueueOverflowError):
            bounded_queue.push(capacity)
        assert (
            bounded_queue.count == capacity
        ), "Bounded queue size should remain at max size after attempting to push to full queue"

    def test_bounded_queue_pop_all_elements(self, bounded_queue):
        capacity = bounded_queue.container.capacity
        for i in range(capacity):
            bounded_queue.push(i)
        for i in range(capacity):
            popped = bounded_queue.pop()
            assert popped == i, f"Popped element should be {i}"
        assert bounded_queue.is_empty is True, "Bounded queue should be empty after popping all elements"
        assert bounded_queue.count == 0, "Bounded queue size should be 0 after popping all elements"


class TestCircularQueue:
    def test_circular_queue_push_wraps_around(self, circular_queue):
        capacity = circular_queue.container.capacity
        for i in range(capacity):
            circular_queue.push(i)
        assert circular_queue.count == capacity, "Circular queue should reach max size after pushing max_size elements"
        with pytest.raises(QueueOverflowError):
            circular_queue.push(capacity)
        assert (
            circular_queue.count == capacity
        ), "Circular queue size should remain at max size after attempting to push to full queue"
        for i in range(capacity):
            popped = circular_queue.pop()
            assert popped == i, f"Popped element should be {i}"
        assert circular_queue.is_empty is True, "Circular queue should be empty after popping all elements"
        assert circular_queue.count == 0, "Circular queue size should be 0 after popping all elements"


class TestDoubleEndedQueue:
    def test_double_ended_queue_push_left_and_right(self, double_ended_queue):
        if double_ended_queue is None:
            pytest.skip("No double-ended queue implementations available")
        double_ended_queue.push(1)
        double_ended_queue.push(2)
        double_ended_queue.push_left(0)
        assert double_ended_queue.count == 3, "Double-ended queue should have size 3 after three pushes"
        assert str(double_ended_queue) == "0, 1, 2", "Double-ended queue string representation should reflect pushes"
        popped = double_ended_queue.pop()
        assert popped == 0, "Popped element should be the first pushed element (from left)"
        assert double_ended_queue.count == 2, "Double-ended queue size should decrease after pop"
        assert (
            str(double_ended_queue) == "1, 2"
        ), "Double-ended queue string representation should reflect current state"
        double_ended_queue.push_left(-1)
        assert double_ended_queue.count == 3, "Double-ended queue should have size 3 after pushing left"
        assert str(double_ended_queue) == "-1, 1, 2", "Double-ended queue string representation should reflect pushes"

    def test_double_ended_queue_push_left_on_non_double_ended_raises_exception(self, single_ended_queue):
        with pytest.raises(QueueOperationNotSupportedError):
            single_ended_queue.push_left(0)
        assert (
            single_ended_queue.is_empty is True
        ), "Queue should remain empty after attempting to push_left on non-deque"
        assert single_ended_queue.count == 0, "Queue size should remain 0 after attempting to push_left on non-deque"

    def test_double_ended_queue_pop_left_and_right(self, double_ended_queue):
        if double_ended_queue is None:
            pytest.skip("No double-ended queue implementations available")
        double_ended_queue.push(1)
        double_ended_queue.push(2)
        double_ended_queue.push_left(0)
        popped_left = double_ended_queue.pop()
        assert popped_left == 0, "Popped element should be the first pushed element (from left)"
        popped_right = double_ended_queue.pop()
        assert popped_right == 1, "Popped element should be the next pushed element"
        assert double_ended_queue.count == 1, "Double-ended queue size should decrease after pops"
        assert str(double_ended_queue) == "2", "Double-ended queue string representation should reflect current state"
