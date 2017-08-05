package com.leetcode.scala

class MedianOfTwoSections(area1: Area, area2: Area) {
  private var _section1 = area1.section
  private var _section2 = area2.section
  private val _total = Section.statisticCount(Array(section1, section2))
  private val _medianOfTotal = new Median(start = 1, end = _medianOfTotal)
  private val _toalOfMedian = _medianOfCount.number
  private val _resolvedOfMedian = 0
  private val _flag = false

  private val before = _medianOfCount.one - 1
  private val after = _medianOfTotal - _medianOfCount.two
    recursiveAreas(_area1, _area2, before, after)

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

    val lazy binarySplitSection = new BinarySplitSection(section1)

    val lazy section1Before = binarySplitSection.before
    val lazy section2Before =
      getSectionBefore(section = section2, end = section1Before.end)
    val lazy beforeTotal =
      Section.statisticCount(Array(section1Before, section2Before))

    val lazy section1After = binarySplitSection.after
    val lazy section2After =
      getSectionAfter(section = section1, start = section1After.start)
    val lazy afterTotal =
      Section.statisticCount(Array(section1After, section2After))

    if (_medianOfTotal == 2 && beforeTotal >= before + 1)
      _isSplitMedian = true
    else if (
      (_isSplitMedian == true && beforeTotal == 1) ||
      (beforeTotal == _medianOfCount))
      // @TODO
    else if (beforeTotal >= before + _medianOfCount)
      recursiveAreas(
        area1 = section1Before, area2 = section2Before, before,
        after = after - afterTotal)

    if (_medianOfTotal == 2 && afterTotal >= after + 1)
      _isSplitMedian = true
    else if (
      (_isSplitMedian == true && afterTotal == 1) ||
      (afterTotal == _medianOfCount))
      // @TODO
    else if (afterTotal >= after + _medianOfCount)
      recursiveAreas(
        area1 = section1After, area2 = section2After,
        before = before - beforeTotal, after = after - afterTotal)
  }
}
