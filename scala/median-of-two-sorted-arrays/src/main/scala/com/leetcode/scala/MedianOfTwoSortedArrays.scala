package com.leetcode.scala

import _root_.scala.Array

class MedianOfTwoSortedArrays[T](val arr1: Array[T], val arr2: Array[T]) {
  private val _arr1 = arr1
  private val _arr2 = arr2
  private val _area1 = new Area(arr = arr1, start = 0, end = _len1)
  private val _area2 = new Area(arr = arr2, start = 0, end = _len2)
  private val _median = getMedian()

  def median = _median

  private def getMedian(): Double = {
    return 0.0
  }
}
