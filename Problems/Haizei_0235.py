"""
# [235. 递归实现指数型枚举](https://oj.haizeix.com/problem/235)

## 题目描述

从 1−n 这 n 个整数中随机选取任意多个，每种方案里的数从小到大排列，按字典序输出所有可能的选择方案。

## 输入

输入一个整数 n（1 ≤ n ≤ 10）

## 输出

每行一组方案，每组方案中两个数之间用空格分隔。

注意每行最后一个数后没有空格。

## 样例

样例输入

> 3

样例输出

> 1
> 1 2
> 1 2 3
> 1 3
> 2
> 2 3
> 3

样例输入2

> 4

样例输出2

> 1
> 1 2
> 1 2 3
> 1 2 3 4
> 1 2 4
> 1 3
> 1 3 4
> 1 4
> 2
> 2 3
> 2 3 4
> 2 4
> 3
> 3 4
> 4

## 数据规模与约定

时间限制：1 s
内存限制：256 M

100% 的数据保证 1 ≤ n ≤ 10
"""

from utils import running_time

"""
定义 f(i, j, n) 表示返回一个从位置 i 开始的最小值为 j 的递归枚举序列
当 j = n 时，f(i, j, n) = None
f(i, j, n) = [
    j && f(i + 1, j + 1, n),
    j && f(i + 1, j + 2, n),
    ...,
    j && f(i + 1, n + 1, n),
    j + 1 && f(i + 1, j + 2, n),
    j + 1 && f(i + 1, j + 3, n),
    ...,
    j + 1 && f(i + 1, n + 1, n),
    ...,
    n && f(i + 1, n + 1, n),
]
"""
result = [0] * 10


def print_result(n):
    for i in range(0, n + 1):
        if i < n:
            print(result[i], end=" ")
        else:
            print(result[i])


def f(i, j, n):
    if j > n:
        return

    for k in range(j, n + 1):
        result[i] = k
        print_result(i)
        f(i + 1, k + 1, n)


@running_time
def run():
    for n in range(1, 11):
        print(f"\n==== n = {n} =====\n")
        f(0, 1, n)


def main():
    n = int(input())
    f(0, 1, n)


if __name__ == "__main__":
    run()
