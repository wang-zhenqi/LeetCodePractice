"""
编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」 定义为：

对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
如果这个过程 结果为 1，那么这个数就是快乐数。
如果 n 是 快乐数 就返回 true ；不是，则返回 false 。

示例 1：
输入：n = 19
输出：true
解释：
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

示例 2：
输入：n = 2
输出：false

提示：
1 <= n <= 2^31 - 1
"""

from utils.utils import running_time


def cal(n: int) -> int:
    r = 0
    while n > 0:
        r += (n % 10) ** 2
        n //= 10
    return r


@running_time
def is_happy(n: int) -> bool:
    s = set()
    new_n = cal(n)
    while new_n != 1:
        if new_n in s:
            return False
        s.add(new_n)
        new_n = cal(new_n)
    return True


@running_time
def is_happy_2(n: int) -> bool:
    slow = n
    fast = cal(n)
    while slow != fast:
        slow = cal(slow)
        fast = cal(cal(fast))
    return fast == 1


def main():
    i = int(input())
    print(is_happy(i))


def test_1():
    inputs = [19, 2, 2849301, 5849220334, 5834747222, 3827344]
    expected = [True, False, False, False, False, True]
    for i, n in enumerate(inputs):
        assert is_happy(n) == expected[i]
    else:
        print("All tests passed.")


def test_2():
    inputs = [19, 2, 2849301, 5849220334, 5834747222, 3827344]
    expected = [True, False, False, False, False, True]
    for i, n in enumerate(inputs):
        assert is_happy_2(n) == expected[i]
    else:
        print("All tests passed.")


if __name__ == "__main__":
    while True:
        main()
