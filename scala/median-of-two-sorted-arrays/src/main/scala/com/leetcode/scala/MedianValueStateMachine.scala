package com.leetcode.scala

import _root_.scala.collection.mutable.ListBuffer

class MedianValueStateMachine[T](count: Int) {
  private lazy val _total = getTotal()
  private lazy val _resolvedList = new ListBuffer[T]()
  private var _resolved = 0
  private var _isFinished = false
  private var _median = 0

  def total = _total
  def isFinished = _isFinished

  def median: Double = {
    if (_isFinished == false) {
      throw IllegalArgumentException(
        "Get property of median, the isFinished must be true")
    } else {
      val one = _resolvedList.apply(0)
      val two = _resolvedList.apply(1)
      Median.calculateValue[T](one, two)
    }
  }

  def +=(median: T): this.type = {
    if (_resolved < _total)
      _resolvedList += median
      _resolved = _resolved + 1

    if (_resolved == _total)
      _isFinished = true
    else
      throw IllegalArgumentException(
        "Resolved count of median shouldn't greater than total")
    this
  }

  private def getTotal(): Int = {
    if ((count >= 1) || (count <= 2))
      count
    else
      throw IllegalArgumentException(
        "Total count of median should within interval [1, 2]")
  }
}
