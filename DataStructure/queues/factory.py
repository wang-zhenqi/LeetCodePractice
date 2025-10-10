from typing import Dict, Tuple, Type

from DataStructure.queues import (
    BoundedDeque,
    BoundedDoubleEndedDeque,
    BoundedDoubleEndedLinkedList,
    BoundedLinkedList,
    CircularBuffer,
    CircularDeque,
    CircularLinkedList,
    Container,
    ContainerConfig,
    Deque,
    DoubleEndedDeque,
    DoubleEndedDynamicArray,
    DoubleEndedFixedArray,
    DoubleEndedLinkedList,
    DynamicArray,
    FixedArray,
    LinkedList,
)
from DataStructure.queues.configs import ContainerT, VariationT

CONTAINER_MAP: Dict[Tuple[bool, ContainerT, VariationT], Type[Container]] = {
    (False, "sequential", None): DynamicArray,
    (True, "sequential", None): FixedArray,
    (True, "sequential", "circular"): CircularBuffer,
    (False, "sequential", "double_ended"): DoubleEndedDynamicArray,
    (True, "sequential", "double_ended"): DoubleEndedFixedArray,
    (False, "linked", None): LinkedList,
    (True, "linked", None): BoundedLinkedList,
    (True, "linked", "circular"): CircularLinkedList,
    (False, "linked", "double_ended"): DoubleEndedLinkedList,
    (True, "linked", "double_ended"): BoundedDoubleEndedLinkedList,
    (False, "deque", None): Deque,
    (True, "deque", None): BoundedDeque,
    (True, "deque", "circular"): CircularDeque,
    (False, "deque", "double_ended"): DoubleEndedDeque,
    (True, "deque", "double_ended"): BoundedDoubleEndedDeque,
}


def create_container(config: ContainerConfig) -> Container:
    bounded: bool = True if config.max_size is not None else False

    key = (bounded, config.implementation, config.variation)
    container_class = CONTAINER_MAP.get(key, None)
    if container_class is None:
        raise ValueError(f"Unsupported strategy for key: {key}")

    return container_class(config.max_size) if bounded else container_class()
