#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from pathlib import Path
import argparse
import sys

class WordMatrix:
    """ 单词矩阵转置操作类

    Attributes:
      matrix: 用于存储矩阵数据的list
    """
    matrix = []

    def __init__(self, text):
        lines = text.split("\n")
        for line in lines:
            if line != "":
                words = line.split(" ")
                self.matrix.append(words)

    @staticmethod
    def get_number_of_rows(matrix):
        """获取矩阵的总行数

        Args:
          matrix: 一个矩阵实例数据

        Returns:
           返回给定矩阵的总行数的整数值
        """
        return len(matrix)

    @staticmethod
    def get_number_of_cols(matrix):
        """获取矩阵的总列数

        Args:
          matrix: 一个矩阵实例数据

        Returns:
           返回给定矩阵的总列数的整数值
        """
        return len(matrix[0])

    @staticmethod
    def format_output_matrix(matrix):
        """根据矩阵格式化输入单词组合文本

        Args:
          matrix: 一个矩阵实例数据

        Returns:
           返回给定矩阵的格式化输入字符串k
        """
        lines = []
        rows = WordMatrix.get_number_of_rows(matrix)
        for row in range(rows):
            lines.append(" ".join(matrix[row]))
        return "\n".join(lines)

    def get_transpose(self):
        """获取当前矩阵数据转置

        Returns:
            返回表示当前转置的矩阵
        """
        matrix = self.matrix
        rows = WordMatrix.get_number_of_rows(matrix)
        cols = WordMatrix.get_number_of_cols(matrix)
        transpose_matrix = [[]] * cols
        for col in range(cols):
            transpose_matrix[col] = [None] * rows
            for row in range(rows):
                transpose_matrix[col][row] = matrix[row][col]
        return transpose_matrix

class TransposeFile:
    """ 倒转文件内容类

    Attributes:
      path: 用户指定的文件路径
      transpose_content: 倒转文件结果
    """
    def __init__(self, file_path):
        path = Path(file_path)
        self.path = path.resolve()

        word_matrix = WordMatrix(text=self.get_file_content())
        transpose_matrix = word_matrix.get_transpose()
        self.transpose_content = WordMatrix.format_output_matrix(transpose_matrix)

    def get_file_content(self):
        """根据命令行参数获取文件内容

        Returns:
          返回含有文件内容的字符串
        """
        path = self.path
        if (path.is_file()):
            file = path.open()
            text = file.read()
            file.close()
            return text

def main():
    """脚本执行主方法

    根据用户传入路径获取文件内容,并进行倒转,结果输入的`stdout`中

    Returns:
      没有返回结果
    """
    parser = argparse.ArgumentParser(
        description="Given a [FILE], transpose its content.")
    parser.add_argument("file", metavar="FILE", type=str, nargs=1,
        help="A directory of a given [FILE]")
    args = parser.parse_args()

    transpose_file = TransposeFile(file_path=args.file[0])
    text = transpose_file.transpose_content
    sys.stdout.write(text)

main()
