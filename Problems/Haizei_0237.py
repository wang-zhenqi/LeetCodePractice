"""
## 题目描述
从 1 − n 这 n 个整数排成一排并打乱次序，按字典序输出所有可能的选择方案。

## 输入

输入一个整数 n。（1≤n≤8）

## 输出

每行一组方案，每组方案中两个数之间用空格分隔。
注意每行最后一个数后没有空格。

## 样例

### 样例输入

3

### 样例输出

1 2 3
1 3 2
2 1 3
2 3 1
3 1 2
3 2 1

## 数据规模与约定

时间限制：1 s
内存限制：256 M
100% 的数据保证 1≤n≤8
"""

from utils import running_time

"""
设 f(l) 表示 list l 中的整数的排列方案。
当 l 为空时，f(l) = []
f(l) = [x] + f(l - x) for x in l
"""

# result = [] * 8

test_cases = [
    {
        "input": 1,
        "output": [
            "1",
        ],
    },
    {
        "input": 2,
        "output": [
            "1 2",
            "2 1",
        ],
    },
    {
        "input": 3,
        "output": [
            "1 2 3",
            "1 3 2",
            "2 1 3",
            "2 3 1",
            "3 1 2",
            "3 2 1",
        ],
    },
    {
        "input": 4,
        "output": [
            "1 2 3 4",
            "1 2 4 3",
            "1 3 2 4",
            "1 3 4 2",
            "1 4 2 3",
            "1 4 3 2",
            "2 1 3 4",
            "2 1 4 3",
            "2 3 1 4",
            "2 3 4 1",
            "2 4 1 3",
            "2 4 3 1",
            "3 1 2 4",
            "3 1 4 2",
            "3 2 1 4",
            "3 2 4 1",
            "3 4 1 2",
            "3 4 2 1",
            "4 1 2 3",
            "4 1 3 2",
            "4 2 1 3",
            "4 2 3 1",
            "4 3 1 2",
            "4 3 2 1",
        ],
    },
]

output = []


def permutation(numbers: list[int], result: list[str]) -> None:
    if len(numbers) == 0:
        print(" ".join(result))
        output.append(" ".join(result))
        return

    numbers = sorted(numbers)

    for i in numbers:
        result.append(str(i))
        new_list = numbers.copy()
        new_list.remove(i)
        permutation(new_list, result)
        result.pop(-1)


@running_time
def run(n):
    permutation(list(range(1, int(n) + 1)), [])


def main():
    n = input()
    run(n)


def test_run():
    for case in test_cases:
        global output
        output = []
        run(case["input"])
        assert output == case["output"]


if __name__ == "__main__":
    main()
