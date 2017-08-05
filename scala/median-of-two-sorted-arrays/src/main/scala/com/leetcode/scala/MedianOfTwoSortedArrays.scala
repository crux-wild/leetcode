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

  private class BinarySplitSection(section: Section) {
    private lazy val _before = section / 2

    def before = _before
    def after: Section = {
    Section.diffTwoSection(section, _before)
      .getOrElse("end(+)", new Section(head = 0, tail = 0))
    }
  }

  private def getSectionBefore(section: Section, end: Int): Section = {
    if (section.end <= end) {
      section
    } else {
      val binarySplitSection = new BinarySplitSection(section)
      val sectionBefore = binarySplitSection.before
      getSectionBefore(section = sectionBefore, end)
    }
  }

  private def getSectionAfter(section: Section, start: Int): Section = {
    if (section.start >= start) {
      section
    } else {
      val binarySplitSection = new BinarySplitSection(section)
      val sectionAfter = binarySplitSection.after
      getSectionBefore(section = sectionAfter, start)
    }
  }

  private def recursiveAreas(
    area1: Area[T], area2: Area[T], before: Int, after: Int): Double = {
    val section1 = _area1.section
    val section2 = _area2.section

    val binarySplitSection = new BinarySplitSection(section1)

    val section1Before = binarySplitSection.before
    val section2Before =
      getSectionBefore(section = section2, end = section1Before.end)

    val section1After = binarySplitSection.after
    val section2After =
      getSectionAfter(section = section1, start = section1After.start)

    return 0.0
  }

  private def getMedian(): Double = {
    val before = _medianOfCount.one - 1
    val after = _count - _medianOfCount.two
    recursiveAreas(_area1, _area2, before, after)
    return 0.0
  }
}
