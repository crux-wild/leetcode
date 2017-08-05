package com.leetcode.scala

import _root_.scala.collection.mutable.IndexedSeq

class MedianOfTwoSortedArrays[T](val arr1: Array[T], val arr2: Array[T]) {
  private val _arr1 = arr1
  private val _arr2 = arr2
  private var _len1 = arr1.length
  private var _len2 = arr2.length
  private val _area1 = new Area(arr = arr1, start = 0, end = _len1)
  private val _area2 = new Area(arr = arr2, start = 0, end = _len2)
  private val _count = len1 + len2
  private val _median = new Median(start = 1, end = count)
  private val _number = _median.number
  private val _median = getMedian()

  def median = _median

  private def binarySplitSection(section: section): Seq[Section]] = {
    val section1 = area.section / 2
    var section2 = area.section - area1.section
    Seq(section1, section2)
  }

  private def recursiveAreas(
    area1: Area[T], area2: Area[T], before: Int, after: Int): Double = {
    val section1 = _area1.section
    val section2 = _area2.section

    val sectionSeq = binarySplitSection(section1)
    val section1Before = sectionSeq.apply(0)
    val section2After = secitonSeq.apply(1)

    return 0.0
  }

  private def getMedian(): Double = {
    val before = _median.one - 1
    val after = _count - _median.two
    recursiveAreas(_area1, _area2, before, after)
    return 0.0
  }
}
