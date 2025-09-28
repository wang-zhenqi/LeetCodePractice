from pydantic import BaseModel

from DataStructure.queues import Container, ContainerConfig
from DataStructure.queues.factory import create_container


class Queue(BaseModel):
    config: ContainerConfig

    @property
    def container(self) -> Container:
        return create_container(self.config)

    @property
    def size(self) -> int:
        return self.container.size

    @property
    def is_empty(self) -> bool:
        return self.container.is_empty
