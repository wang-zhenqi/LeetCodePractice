"""
# [186. 弹簧板](https://oj.haizeix.com/problem/186)

## 题目描述

有一个小球掉落在一串连续的弹簧板上，小球落到某一个弹簧板后，会被弹到某一个地点，直到小球被弹到弹簧板以外的地方。

假设有 n 个连续的弹簧板，每个弹簧板占一个单位距离，a[i] 代表代表第 i 个弹簧板会把小球向前弹 a[i] 个距离。
比如位置 1 的弹簧能让小球前进 2 个距离到达位置 3。
如果小球落到某个弹簧板后，经过一系列弹跳会被弹出弹簧板，那么小球就能从这个弹簧板弹出来。

现在小球掉到了1 号弹簧板上面，那么这个小球会被弹起多少次，才会弹出弹簧板。1 号弹簧板也算一次。

## 输入

第一个行输入一个 n 代表一共有 n (1 ≤ n ≤ 100000) 个弹簧板。

第二行输入 n 个数字，中间用空格分开。第 i 个数字 a[i] (0 < a[i] ≤ 30) 代表第 i 个弹簧板可以让小球移动的距离。

## 输出
输出一个整数，表示小球被弹起的次数。

## 样例

样例输入1
> 5
> 2 2 3 1 2

样例输出1
> 2

样例输入2
> 5
> 1 2 3 1 2

样例输出2
> 4

## 数据规模与限定
时间限制：1 s
内存限制：64 M
"""
import loguru

from utils import running_time

"""
设 f(x) 表示小球落在 x 号弹簧板上，可以弹多少次才会弹出去。
当小球落在位置 k 时，小球会被弹到位置 k + a[k].
如果 k >= n，则 f(k) = 0
f(k) = 1 + f(k + a[k])
"""


def bounce_count(x, a):
    return 0 if x >= len(a) else 1 + bounce_count(x + a[x], a)


@running_time
def bounce(a):
    return bounce_count(0, a)


def test_main():
    test_cases = [
        ((5, "2 2 3 1 2"), 2),
        ((5, "1 2 3 1 2"), 4),
    ]

    for test_case in test_cases:
        expected = test_case[1]
        actual = bounce([int(i) for i in test_case[0][1].split(" ")])

        if actual == expected:
            loguru.logger.success("PASS")
        else:
            loguru.logger.error(f"FAIL: expected = {expected}, actual = {actual}")


def main():
    input()
    a = [int(i) for i in input().split(" ")]

    loguru.logger.info(bounce(a))


if __name__ == "__main__":
    test_main()

    main()
