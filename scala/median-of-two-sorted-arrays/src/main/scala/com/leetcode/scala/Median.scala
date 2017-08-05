package com.leetcode.scala

import _root_.scala.math

class Median(val start: Int, val end: Int) {
  private val _length = getLength()
  private val _isEven = (_length % 2) == 0
  private val _offset = getOffset()
  private val _one = _offset + start - 1
  private val _two = if (_isEven) _one + 1 else _one
  private val _number = if (_isEven) 2 else 1
  private val _value = if(_isEven) (_one + _two) / 2 else _one

  def one = _one
  def two = _two
  def value = _value
  def number = _number

  private def getLength(): Int =
    if (end > start)
      end - start + 1
    else if (end < start)
      throw IllegalArgumentException(
        "constructor param:[end] is lesser than param:[start];")
    else 2
  private def getOffset(): Int =
    if (_isEven)
      _length / 2
    else
      math.ceil(_length / 2).toInt
}

case class IllegalArgumentException(
  val message: String = "",
  val cause: Throwable = None.orNull)
extends Exception(message, cause)
