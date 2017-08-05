package com.leetcode.scala

import _root_.scala.math

import scala.IllegalArgumentException

class Median(val start: Int, val end: Int) {
  private val _length = getLength()
  private val _isEven = (_length % 2) == 0
  private val _offset = getOffset()
  private val _one = _offset + start - 1
  private val _two = if (_isEven) _one + 1 else _one
  private val _number = if (_isEven) 2 else 1
  private val _value = if (_isEven) (_one + _two) / 2 else _one

  def one = _one
  def two = _two
  def value = _value
  def number = _number

  private def getLength(): Int = {
    if (end < start)
      throw IllegalArgumentException(
        "Argument end shouldn't lesser than argument start")
    else if (end > start)
      end - start + 1
    else 2
  }

  private def getOffset(): Int = {
    val median = _length / 2
    if (_isEven)
      median
    else
      math.ceil(median).toInt
  }
}

object Median {
  def calculateValue(one: Int, two: Int): Double = {
    if (one + 1 == two)
      (one + two) / 2
    else if (one == two)
      one
    else
      throw IllegalArgumentException(
        "Argument two should with interval [one, one + 1]")
  }
}
