import time

import loguru


def running_time(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        end = time.time()
        loguru.logger.debug(f"Total running time: {end - start:.3f} seconds")
        return result

    return wrapper


if __name__ == "__main__":

    @running_time
    def test():
        for i in range(1000000):
            pass

    test()
