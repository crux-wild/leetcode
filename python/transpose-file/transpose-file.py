#!/usr/bin/env python3
# -*- coding: utf-8 -*-

class WordMatrix:
  matrix = []

  def __init__(self, string):
    lines = string.split("\n")
    for line in lines:
      words = line.split(" ")
      self.matrix.append(words)

  def getNumberOfRows(self):
    return len(self.matrix)

  def getNumberOfCols(self):
    return len(self.matrix[0])

  def getTranspose(self):
    rows = self.getNumberOfRows()
    cols = self.getNumberOfCols()
    matrix = [[]] * cols
    for col in range(cols):
      matrix[col] = [ None ] * rows
      for row in range(rows):
        matrix[col][row] = self.matrix[row][col]
    return matrix
