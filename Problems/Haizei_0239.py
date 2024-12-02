"""
# [239. 不规则的街道](https://oj.haizeix.com/problem/239)

## 题目描述

城市扩建的规划是个令人头疼的大问题。规划师设计了一个极其复杂的方案：当城市规模扩大之后，把与原来城市结构一样的区域复制或旋转 90 度之后建设在原来的城市周围（详细地说，将原来的城市复制一遍放在原城市上方，
将顺时针旋转 90 度后的城市放在原城市的左上方，将逆时针旋转 90 度后的城市放在原城市的左方），再用道路将四部分首尾连接起来，如下图所示。

![img](https://img.haizeix.com/img/%E7%AE%97%E6%B3%95%E7%AB%9E%E8%B5%9B/0x03-16-3889.jpg)

容易看出，扩建后的城市的各个房屋仍然由一条道路连接。定义 N 级城市为拥有 2^2N 座房屋的城市。对于任意等级的城市，从左上角开始沿着唯一的道路走，依次为房屋标号，就能够得到每间房及的编号了。
住在其中两间房屋里的人们想知道，如果城市发展到了一定等级，他俩各自所处的房屋之间的直线距离是多少。你可以认为图中的每个格子都是边长为 10 米的正方形，房屋均位于每个格子的中心点上。
T 次询问，每次输入等级 N，两个编号 S、D，求 S 与 D 之间的直线距离。

## 输入

第一行输入一个整数 T。
接下来 T 行，每行输入三个整数 N,S,D。（1 ≤ T ≤ 104, 1 ≤ N ≤ 31）

## 输出

对于每次询问输出一行一个整数，表示两点间的直线距离。输出四舍五入到整数。

## 样例

样例输入

> 3
> 1 1 2
> 2 16 1
> 3 4 33

样例输出

> 10
> 30
> 50

## 数据规模与约定
时间限制：1 s
内存限制：256 M
100% 的数据保证 1 ≤ T ≤ 104, 1 ≤ N ≤ 31
"""

import pytest

from utils.utils import running_time


def get_coordination_of_house(level: int, s: int) -> tuple[int, int]:
    if level == 1:
        if s == 1:
            return 0, 0
        elif s == 2:
            return 0, 1
        elif s == 3:
            return 1, 1
        else:
            return 1, 0
    else:
        n = 2 ** (level - 1)
        block = 2 ** (2 * (level - 1))
        if s <= block:
            x, y = get_coordination_of_house(level - 1, s)
            return y, x
        elif s <= 2 * block:
            x, y = get_coordination_of_house(level - 1, s - block)
            return x, y + n
        elif s <= 3 * block:
            x, y = get_coordination_of_house(level - 1, s - 2 * block)
            return x + n, y + n
        else:
            x, y = get_coordination_of_house(level - 1, s - 3 * block)
            return 2 * n - y - 1, n - x - 1


@pytest.mark.parametrize(
    "level, s, expected",
    [
        (1, 1, (0, 0)),
        (2, 8, (1, 2)),
        (3, 33, (4, 4)),
        (3, 4, (1, 0)),
    ],
)
def test_get_coordination_of_house_should_return_the_x_and_y_when_given_the_house_number(level, s, expected):
    assert get_coordination_of_house(level, s) == expected


@running_time
def run(n: int, s: int, d: int) -> int:
    x1, y1 = get_coordination_of_house(n, s)
    x2, y2 = get_coordination_of_house(n, d)
    return round(((x1 - x2) ** 2 + (y1 - y2) ** 2) ** 0.5 * 10)


@pytest.mark.parametrize(
    "d, n, s, expected",
    [
        (1, 1, 2, 10),
        (2, 16, 1, 30),
        (3, 4, 33, 50),
    ],
)
def test_run(d, n, s, expected):
    assert expected == run(d, n, s)


def main():
    t = int(input())
    for _ in range(t):
        n, s, d = map(int, input().split())
        print(run(n, s, d))


if __name__ == "__main__":
    main()
