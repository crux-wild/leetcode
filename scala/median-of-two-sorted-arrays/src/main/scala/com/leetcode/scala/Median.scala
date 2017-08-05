package com.leetcode.scala

import _root_.scala.math

class Median(val start: Int, val end: Int) {
  private val _length = getCount()
  private val _isEven = (count % 2) == 0
  private val _one = getIndex(getOffset(_count))
  private val _two = if (_isEven) _one + 1 else _one
  private val _number = if (_isEven) 2 else 1

  def one = _one
  def two = _two
  def number = _number

  private def getLength(): Int =
    if (end > start)
      end - start + 1
    else if (end < start)
      throw IllegalArgumentException(
        "param:[end] is lesser than param:[start];")
    else 2

  private def getOffset(length: Int): Int =
    if (_isEven) length / 2 else math.ceil(length / 2).toInt

  private def getIndex(offset: Int): Int = offset + start - 1
}

case class IllegalArgumentException(
  val message: String = "",
  val cause: Throwable = None.orNull) extends Exception(message, cause)
