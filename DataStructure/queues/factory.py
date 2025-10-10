from typing import Dict, Literal, Tuple, Type, TypeAlias

from pydantic import BaseModel, Field, model_validator

from DataStructure.queues.containers import (
    BoundedDeque,
    BoundedDoubleEndedDeque,
    BoundedDoubleEndedLinkedList,
    BoundedLinkedList,
    CircularBuffer,
    CircularDeque,
    CircularLinkedList,
    Container,
    Deque,
    DoubleEndedDeque,
    DoubleEndedDynamicArray,
    DoubleEndedFixedArray,
    DoubleEndedLinkedList,
    DynamicArray,
    FixedArray,
    LinkedList,
)

ContainerT: TypeAlias = Literal["sequential", "linked", "deque"]
VariationT: TypeAlias = Literal["circular", "double_ended"] | None


class ContainerConfig(BaseModel):
    max_size: int | None = Field(default=None, ge=1)
    implementation: ContainerT = "sequential"
    variation: VariationT = None

    @model_validator(mode="after")
    def validate_circular_requires_max_size(self):
        if self.variation == "circular" and self.max_size is None:
            raise ValueError("circular container must have max_size set")
        return self

    @model_validator(mode="after")
    def validate_max_size_positive(self):
        if self.max_size is not None and self.max_size < 1:
            raise ValueError("max_size must be at least 1")
        return self


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
