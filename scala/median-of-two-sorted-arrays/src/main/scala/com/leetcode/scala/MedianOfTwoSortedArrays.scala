package com.leetcode.scala

import _root_.scala.Array

class MedianOfTwoSortedArrays[T](val arr1: Array[T], val arr2: Array[T]) {
  private val _arr1 = arr1
  private val _arr2 = arr2
  private val _len1 = arr1.length
  private val _len2 = arr2.length
  private val _area1 = new Area(arr = arr1, start = 0, end = _len1 - 1)
  private val _area2 = new Area(arr = arr2, start = 0, end = _len2 - 1)
  private val _total = getTotal()
  private var _median = getMedian()

  def median = _median

  private def getTotal(): Int = {
    val section1 = _area1.section
    val section2 = _area2.section
    Section.statisticCount(Array(section1, section2))
  }

  private def getMedian(): Double = {
    val median = new Median(start = 1, end = _total)
    val before = median.one - 1
    val after = _total - median.two

    new BinarySplitSolution[T](_area1, _area2, before, after).median
  }
}
