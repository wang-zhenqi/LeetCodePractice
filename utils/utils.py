from datetime import time


def running_time(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        end = time.time()
        print(f"Total running time: {end - start:.2f} seconds")
        return result

    return wrapper
