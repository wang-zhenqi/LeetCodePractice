# queues.py
from typing import List, Literal

from pydantic import BaseModel, Field, computed_field


class Queue(BaseModel):
    max_size: int | None = Field(default=None, ge=1)
    implementation: Literal["sequential", "linked", "deque"] = "sequential"
    variation: Literal["circular", "deque"] | None = None

    _items: List[int] = []

    @computed_field
    @property
    def size(self) -> int:
        return len(self._items)

    @computed_field
    @property
    def is_empty(self) -> bool:
        return self.size == 0
