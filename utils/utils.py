import sys
import time

import loguru

loguru.logger.remove()
loguru.logger.add(sys.stdout, level="INFO")


def running_time(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        end = time.time()
        loguru.logger.info(f"Total running time: {end - start:.2f} seconds")
        return result

    return wrapper


if __name__ == "__main__":

    @running_time
    def test():
        for i in range(1000000):
            pass

    test()
