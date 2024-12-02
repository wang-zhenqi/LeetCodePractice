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


def join_parts(
    top_left: list[list[int]],
    top_right: list[list[int]],
    bottom_right: list[list[int]],
    bottom_left: list[list[int]],
) -> list[list[int]]:
    n = len(top_left)
    n_square = n**2

    part1 = [[item for item in row] for row in top_left]
    part2 = [[item for item in row] for row in top_right]
    part3 = [[item for item in row] for row in bottom_right]
    part4 = [[item for item in row] for row in bottom_left]

    for i in range(n):
        for j in range(n):
            part2[i][j] = part2[i][j] + n_square
    top = [part1[i] + part2[i] for i in range(n)]

    for i in range(n):
        for j in range(n):
            part3[i][j] = part3[i][j] + 2 * n_square
            part4[i][j] = part4[i][j] + 3 * n_square
    bottom = [part4[i] + part3[i] for i in range(n)]
    return top + bottom


def rotate_left_and_flip(matrix: list[list[int]]) -> list[list[int]]:
    """
    origin -> reverse every line -> transpose -> reverse every line
    1 2     2 1     2 3     3 2
    4 3     3 4     1 4     4 1

    1 4 5 6             6 5 4 1         6 7 10 11       11 10 7 6
    2 3 8 7             7 8 3 2         5 8 9  12       12 9  8 5
    15 14 9 10          10 9 14 15      4 3 14 13       13 14 3 4
    16 13 12 11         11 12 13 16     1 2 15 16       16 15 2 1
    """
    n = len(matrix)
    column_reversed_matrix = [line[::-1] for line in matrix]
    transposed_matrix = [[column_reversed_matrix[j][i] for j in range(n)] for i in range(n)]
    return [line[::-1] for line in transposed_matrix]


def rotate_right_and_flip(matrix: list[list[int]]) -> list[list[int]]:
    """
    origin -> transpose
    1 2     1 4
    4 3     2 3

    1 4 5 6             1 2 15 16
    2 3 8 7             4 3 14 13
    15 14 9 10          5 8 9  12
    16 13 12 11         6 7 10 11
    """
    n = len(matrix)
    return [[matrix[j][i] for j in range(n)] for i in range(n)]


def construct_city(level: int) -> list:
    if level == 0:
        return [[1]]

    next_level = construct_city(level - 1)
    return join_parts(rotate_right_and_flip(next_level), next_level, next_level, rotate_left_and_flip(next_level))


@pytest.mark.parametrize(
    "top_left, top_right, bottom_right, bottom_left, expected",
    [
        ([[1]], [[1]], [[1]], [[1]], [[1, 2], [4, 3]]),
        (
            [[1, 4], [2, 3]],
            [[1, 2], [4, 3]],
            [[1, 2], [4, 3]],
            [[3, 2], [4, 1]],
            [[1, 4, 5, 6], [2, 3, 8, 7], [15, 14, 9, 10], [16, 13, 12, 11]],
        ),
    ],
)
def test_join_parts_should_combine_the_four_matrice_into_a_big_one(
    top_left, top_right, bottom_right, bottom_left, expected
):
    assert join_parts(top_left, top_right, bottom_right, bottom_left) == expected


@pytest.mark.parametrize(
    "origin, expected",
    [
        ([[1]], [[1]]),
        ([[1, 2], [4, 3]], [[1, 4], [2, 3]]),
        (
            [[1, 4, 5, 6], [2, 3, 8, 7], [15, 14, 9, 10], [16, 13, 12, 11]],
            [[1, 2, 15, 16], [4, 3, 14, 13], [5, 8, 9, 12], [6, 7, 10, 11]],
        ),
    ],
)
def test_rotate_right_and_flip_should_convert_a_matrix_to_a_new_form(origin, expected):
    assert rotate_right_and_flip(origin) == expected


@pytest.mark.parametrize(
    "origin, expected",
    [
        ([[1]], [[1]]),
        ([[1, 2], [4, 3]], [[3, 2], [4, 1]]),
        (
            [[1, 4, 5, 6], [2, 3, 8, 7], [15, 14, 9, 10], [16, 13, 12, 11]],
            [[11, 10, 7, 6], [12, 9, 8, 5], [13, 14, 3, 4], [16, 15, 2, 1]],
        ),
    ],
)
def test_rotate_left_and_flip_should_convert_a_matrix_to_a_new_form(origin, expected):
    assert rotate_left_and_flip(origin) == expected


@pytest.mark.parametrize(
    "level, expected",
    (
        (
            1,
            [
                [1, 2],
                [4, 3],
            ],
        ),
        (
            2,
            [
                [1, 4, 5, 6],
                [2, 3, 8, 7],
                [15, 14, 9, 10],
                [16, 13, 12, 11],
            ],
        ),
        (
            3,
            [
                [1, 2, 15, 16, 17, 20, 21, 22],
                [4, 3, 14, 13, 18, 19, 24, 23],
                [5, 8, 9, 12, 31, 30, 25, 26],
                [6, 7, 10, 11, 32, 29, 28, 27],
                [59, 58, 55, 54, 33, 36, 37, 38],
                [60, 57, 56, 53, 34, 35, 40, 39],
                [61, 62, 51, 52, 47, 46, 41, 42],
                [64, 63, 50, 49, 48, 45, 44, 43],
            ],
        ),
    ),
)
def test_construct_city_of_level_n_should_return_a_valid_matrix_with_houses_id(level, expected):
    assert construct_city(level) == expected


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
