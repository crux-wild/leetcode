package com.leetcode.scala

class MedianOfTwoAreas[T](area1: Area, area2: Area) {
  private var _section1 = area1.section
  private var _section2 = area2.section
  private val _total = Section.statisticCount(Array(section1, section2))
  private val _medianValue = new MedianValueStateMachine(_total)
  private val _flag = false
  private var _median = getMedian()

  def median = _median

  private class BinarySplitSection(section: Section) {
    private lazy val _before = section / 2

    def before = _before
    def after: Section = {
      Section.diffTwoSection(section, _before)
        .getOrElse("end(+)", Nil)
    }
  }

  private def getPortionOfSection(portion: String)(section: Section, bound: Int) {
    val sectionBound = portion match {
      case "before" => section.start
      case "after" => section.end
    }

    if (sectionBound <= bound) {
      section
    } else {
      val binarySplitSections = new BinarySplitSection(section)
      val binarySplitSections = portion.match {
        case "before" => binarySplitSections.before
        case "after" => binarySplitSections.after
      }
      getPortionOfSection(portion)(section, bound)
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

  private getMedian(): T = {
    val median = new Median(start = 1, end = _total)
    val before = median.one - 1
    val after = _total - median.two

    recursiveAreas(_area1, _area2, before, after)
  }
}
