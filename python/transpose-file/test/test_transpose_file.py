import unittest

from ..src import transpose_file

class TestTransposeFile(unittest.TestCase):
    """单词矩阵转置测试用例
    """

    def test_word_matrix(self):
        WordMatrix = transpose_file.WordMatrix
        file_content ="name age\nalice 21\nryan 30"
        word_matrix = WordMatrix(text=file_content)

        transpose_matrix = word_matrix.get_transpose()
        transpose_file_content = WordMatrix.format_output_matrix(transpose_matrix)

        self.assertEqual(transpose_file_content,"name alice ryan\nage 21 30\n")

if __name__ == '__main__':
    unittest.main()
