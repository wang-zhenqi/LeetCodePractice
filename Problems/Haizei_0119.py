"""
# [119. 昨天和明天](https://oj.haizeix.com/training-ground/50/problem/119)

## 题目描述

输入一个日期，计算这个日期前一天和后一天的日期。

## 输入

输入三个整数 y, m, d 分别表示年月日（1000 ≤ y ≤ 3000, 1 ≤ m ≤ 12, 日期必合法）。

## 输出

第一行输出三个用空格分隔的整数，表示前一天的年月日。
第二行输出三个用空格分隔的整数，表示后一天的年月日。

## 样例

样例输入
1900 2 28

样例输出
1900 2 27
1900 3 1

## 数据规模与约定

时间限制：1 s
内存限制：256 M
100% 的数据保证 1000 ≤ y ≤ 3000, 1 ≤ m ≤ 12, 日期必合法
"""

import pytest

from utils.utils import running_time


def is_leap_year(year: int) -> bool:
    if year % 400 == 0:
        return True
    elif year % 4 == 0 and year % 100 != 0:
        return True
    else:
        return False


def get_max_day(year: int, month: int) -> int:
    if month in [1, 3, 5, 7, 8, 10, 12]:
        return 31
    if month in [4, 6, 9, 11]:
        return 30
    if is_leap_year(year):
        return 29
    else:
        return 28


@running_time
def run(year: int, month: int, day: int) -> tuple[str, str]:
    if month == 1 and day == 1:
        return f"{year - 1} 12 31", f"{year} 1 2"
    if month == 12 and day == 31:
        return f"{year} 12 30", f"{year + 1} 1 1"

    if day == get_max_day(year, month):
        return f"{year} {month} {day - 1}", f"{year} {month + 1} 1"
    if day == 1:
        return f"{year} {month - 1} {get_max_day(year, month - 1)}", f"{year} {month} 2"
    return f"{year} {month} {day - 1}", f"{year} {month} {day + 1}"


@pytest.mark.parametrize(
    "y, m, d, expected",
    [
        (1900, 2, 28, ("1900 2 27", "1900 3 1")),
        (2003, 1, 1, ("2002 12 31", "2003 1 2")),
        (1284, 3, 1, ("1284 2 29", "1284 3 2")),
    ],
)
def test_run_should_return_yesterday_and_tomorrow_of_given_date(y, m, d, expected):
    assert run(y, m, d) == expected


def main():
    y, m, d = map(int, input().split())
    yesterday, tomorrow = run(y, m, d)
    print(yesterday)
    print(tomorrow)


if __name__ == "__main__":
    main()
