class QueueEmptyError(Exception):
    pass


class QueueOverflowError(Exception):
    pass


class QueueElementTypeError(Exception):
    pass


class QueueOperationNotSupportedError(Exception):
    pass
