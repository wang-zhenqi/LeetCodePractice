"""
# [184. 路飞吃桃](https://oj.haizeix.com/problem/184)

## 题目描述

路飞买了一堆桃子不知道个数，第一天吃了一半的桃子，还不过瘾，又多吃了一个。以后他每天吃剩下的桃子的一半还多一个，\
到 n 天只剩下一个桃子了。路飞想知道一开始买了多少桃子。

## 输入

输入一个整数 n (2 ≤ n ≤ 30)

## 输出

输出买的桃子的数量。

## 样例

样例输入1
2
样例输出1
4

样例输入2
3
样例输出2
10

## 数据规模与限定

时间限制：1 s
内存限制：64 M
"""
import loguru

from utils import running_time

"""
设 p(x) 为倒数第 x 天原本有的桃子数，则当 x = n 时，表示第 1 天还没吃的时候拥有的桃子数，即一开始的总数。

p(1) = 1 -> 第 n 天 原有 1 个桃子
p(2) = (1 + 1) * 2 -> 第 n - 1 天，原有 4 个桃子
...
p(k) = (p(k - 1) + 1) * 2 -> 第 k + 1 天，原有的桃子是后一天所吃桃子加 1 后的 2 倍

当 k = n 时，得到问题的结果
"""


def the_total_amount_of_peaches_at_the_beginning_of_the_nth_day_before(the_nth_day_before):
    if the_nth_day_before == 1:
        return 1

    return the_total_amount_of_peaches_at_the_beginning_of_the_nth_day_before(the_nth_day_before - 1) * 2 + 2


@running_time
def eat_peaches(n):
    return the_total_amount_of_peaches_at_the_beginning_of_the_nth_day_before(n)


def test_main():
    test_cases = [
        (2, 4),
        (3, 10),
    ]

    for test_case in test_cases:
        expected = test_case[1]
        actual = eat_peaches(test_case[0])

        if actual == expected:
            loguru.logger.success("PASS")
        else:
            loguru.logger.error(f"FAIL: expected = {expected}, actual = {actual}")


def main():
    for i in range(2, 31):
        loguru.logger.info(f"p({i}) = {eat_peaches(i)}")


if __name__ == "__main__":
    test_main()

    main()
