package com.leetcode.scala

import _root_.scala.math

import scala.IllegalArgumentException

class Median(val start: Int, val end: Int) {
  private lazy val _length = getLength()
  private lazy val _isEven = (_length % 2) == 0
  private lazy val _offset = getOffset()
  private lazy val _one = _offset + start - 1
  private lazy val _two = if (_isEven) _one + 1 else _one
  private lazy val _number = if (_isEven) 2 else 1
  private lazy val _value = if (_isEven) (_one + _two) / 2 else _one

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
    else
      2
  }

  private def getOffset(): Int = {
    val average = _length.asInstanceOf[Double] / 2
    if (_isEven)
      average.toInt
    else
      math.ceil(average).toInt
  }
}

object Median {
  def calculateValue[T](one: T, two: T): Double = {
    if (one == two)
      one.asInstanceOf[Double]
    /**
     * 如果不是数字类型这里的求解平均数就没有实际意义
     *  如果是数字类型的变量，都可以通过变化成`Double`类型,求取最精确结果
     */
    else
      (one.asInstanceOf[Double] + two.asInstanceOf[Double]) / 2
  }
}
