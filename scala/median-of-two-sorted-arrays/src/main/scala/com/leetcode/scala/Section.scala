package com.leetcode.scala

import _root_.scala.math

class Median(val start: Int, val end: Int) {
  private val _count = getCount()
  private val _isEven = isEvenNumber(_count)
  private val _one = getMedianOne()
  private val _two = getMedianTwo()

  val number = if (_isEven) 2 else 1

  def one = _one
  def two = _two

  private def getCount(): Int = {
    if (end > start)
      end - start + 1
    else if (end == start)
      2
    else
      throw new IllegalArgumentException(
        message = "Constrcutor param:[end] lesser than param:[start];")
  }

  private def isEvenNumber(count: Int): Boolean = (count % 2) == 0

  private def getMedianOne(): Int = getIndex(getOffset(_count))

  private def getMedianTwo(): Int = if (_isEven) _one + 1 else _one

  private def getIndex(offset: Int): Int = offset + start - 1

  private def getOffset(count: Int): Int =
    if (_isEven) count / 2 else math.ceil(count / 2).toInt
}

case class IllegalArgumentException(val message: String = "")
  extends Exception(message)
