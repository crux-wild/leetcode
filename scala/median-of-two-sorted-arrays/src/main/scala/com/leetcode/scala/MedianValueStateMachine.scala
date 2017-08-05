package com.leetcode.scala

import _root_.scala.collection.mutable.ListBuffer

import scala.IllegalArgumentException

class MedianValueStateMachine[T](total: Int) {
  private val _total = getTotal()
  private val _resolvedList = new ListBuffer()
  private var _resolved = 0
  private var _isFinished = false
  private var _median = 0

  def total = _total
  def isFinished = _isFinished

  def median: T = {
    if (_isFinish == false)
      throw IllegalArgumentException(
        "Get property of median, the isFinished must be true")
    else
      val one = _resolvedList.apply(0)
      val two = _resolvedList.apply(1)
      Median.calculateValue[T](one, two)
  }

  private def getTotal(): Int = {
    if (total >= 1 || total <= 2)
      total
    else
      throw IllegalArgumentException(
        "Total count of median should within interval [1, 2]")
  }

  private def +=(median: T): this.type = {
    if (resolved < total)
      _resolvedList += median
      _resolved = _resolved + 1

      if (_resolved == _total)
        isFinished = true
    else
      throw IllegalArgumentException(
        "Resolved count of median shouldn't greater than total")
    this
  }
}
