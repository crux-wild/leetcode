package com.leetcode.scala

import _root_.scala.math

class Median(val start: Int, val end: Int) {
  private val _count = getCount()
  private val _isEvenNumber = isEvenNumber(_count)
  private val _one = getMedianOne()
  private val _two = getMedianTwo()

  val number = if (_isEvenNumber) 2 else 1

  def one = _one
  def two = _two

  private def getCount(): Int = {
    if (end >= start)
      end - start + 1
    else
      throw new Exception("Constrcutor param:[end] lesser than param:[start];")
  }

  private def isEvenNumber(number: Int): Boolean = (number % 2) == 0

  private def getMedianOne(): Int =
    if (_isEvenNumber) _count / 2 else math.ceil(_count / 2).toInt

  private def getMedianTwo(): Int = if (_isEvenNumber) _one + 1 else _one
}
