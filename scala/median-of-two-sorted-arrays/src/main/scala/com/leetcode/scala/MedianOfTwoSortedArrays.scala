package com.leetcode.scala

class MedianOfTwoSortedArrays[T](val arr1: Array[T], val arr2: Array[T]) {
  private val _arr1 = arr1
  private val _arr2 = arr2
  private var _len1 = arr1.length
  private var _len2 = arr2.length
  private val _count = _len1 + _len2
  private val _area1 = new Area(arr = arr1, start = 0, end = _len1)
  private val _area2 = new Area(arr = arr2, start = 0, end = _len2)
  private val _medianOfCount = new Median(start = 1, end = _count)
  private val _number = _medianOfCount.number
  private val _median = getMedian()
  def median = _median

  private def getSectionBefore(section: Section, end: Int): Section = {
    var sectionBefore = section
    var sectionSeq = Nil

    if (sectionBefore.end <= end)
      section
    else
      sectionSeq = binarySplitSection(section)
      sectionBefore = sectionSeq.apply(0)
      getSectionBefore(section = sectionBefore, end)
  }

  private def getSectionAfter(section: Section, start: Int): Section = {
    var sectionAfter = section
    var sectionSeq = Nil

    if (sectionAfter.start >= start)
      section
    else
      sectionSeq = binarySplitSection(section)
      sectionAfter = sectionSeq.apply(1)
      getSectionBefore(section = sectionAfter, start)
  }

  private def binarySplitSection(section: Section): Seq[Section] = {
    val section1 = section / 2
    var section2 = section - section
    new Seq(section1, section2)
  }

  private def recursiveAreas(
    area1: Area[T], area2: Area[T], before: Int, after: Int): Double = {
    val section1 = _area1.section
    val section2 = _area2.section

    val sectionSeq = binarySplitSection(section1)

    val section1Before = sectionSeq.apply(0)
    val section2Before =
      getSectionBefore(section = section2, end = section1Before.end)

    val section2After = sectionSeq.apply(1)
    val section2After =
      getSectionAfter(section = section1, start = section2After.start)

    return 0.0
  }

  private def getMedian(): Double = {
    val before = _medianOfCount.one - 1
    val after = _count - _medianOfCount.two
    recursiveAreas(_area1, _area2, before, after)
    return 0.0
  }
}
