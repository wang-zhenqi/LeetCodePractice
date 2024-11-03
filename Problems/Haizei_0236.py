"""
## 题目描述

从 1−n 这 n 个整数中随机选取 m 个，每种方案里的数从小到大排列，按字典序输出所有可能的选择方案。

## 输入

输入两个整数 n,m。（1≤m≤n≤10）

输出

每行一组方案，每组方案中两个数之间用空格分隔。
注意每行最后一个数后没有空格。

## 样例

样例输入

3 2

样例输出

1 2
1 3
2 3

样例输入2

5 3

样例输出2

1 2 3
1 2 4
1 2 5
1 3 4
1 3 5
1 4 5
2 3 4
2 3 5
2 4 5
3 4 5

##数据规模与约定

时间限制：1 s
内存限制：256 M

100% 的数据保证 1 ≤ m ≤ n ≤ 10
"""

from utils import running_time

"""
定义 f(i, j, n, m) 表示从位置 i 开始的最小值为 j 的递归枚举序列，其中 n 为该位置可选的最大值，m 为结果的数字个数
当 i == m 时，表示已经选取了 m 个数字，输出结果

f(i, j, n, m) = [
    j && f(i + 1, j + 1, n, m),
    j && f(i + 1, j + 2, n, m),
    ...
    j + 1 && f(i + 1, j + 2, n, m),
    ...
]
"""

result = [0] * 10


def f(i, j, n, m):
    if i == m:
        print(" ".join(map(str, result[:i])))
        return

    for k in range(j, n - m + i + 2):
        result[i] = k
        f(i + 1, k + 1, n, m)


@running_time
def main():
    user_input = input()
    n, m = tuple(map(int, user_input.split(" ")))
    f(0, 1, n, m)


if __name__ == "__main__":
    main()
