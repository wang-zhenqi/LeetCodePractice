# 给你一个 m 行 n 列的矩阵matrix ，请按照顺时针螺旋顺序 ，返回矩阵中的所有元素。

# 示例
# 1：
# 输入：matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
# 输出：[1, 2, 3, 6, 9, 8, 7, 4, 5]

# 示例
# 2：
# 输入：matrix = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
# 输出：[1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

# 提示：
# m == matrix.length
# n == matrix[i].length
# 1 <= m, n <= 10
# -100 <= matrix[i][j] <= 100


class Solution:
    def spiralOrder(self, matrix):
        if not matrix or not matrix[0]:
            return []

        result = []
        rows, cols = len(matrix), len(matrix[0])
        up, down, left, right = 0, rows - 1, 0, cols - 1
        direction = 0  # 0: right, 1: down, 2: left, 3: up

        while left <= right and up <= down:
            if direction == 0:  # Move right
                for i in range(left, right + 1):
                    result.append(matrix[up][i])
                up += 1
            elif direction == 1:  # Move down
                for i in range(up, down + 1):
                    result.append(matrix[i][right])
                right -= 1
            elif direction == 2:  # Move left
                for i in range(right, left - 1, -1):
                    result.append(matrix[down][i])
                down -= 1
            elif direction == 3:  # Move up
                for i in range(down, up - 1, -1):
                    result.append(matrix[i][left])
                left += 1

            direction = (direction + 1) % 4

        return result


if __name__ == "__main__":
    result = Solution().spiralOrder([[1, 2, 3, 4, 5, 6, 7], [8, 9, 10, 11, 12, 13, 14], [15, 16, 17, 18, 19, 20, 21]])
    print(result)
