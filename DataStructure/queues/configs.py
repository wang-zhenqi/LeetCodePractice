from typing import Literal, TypeAlias

from pydantic import BaseModel, Field, model_validator

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
