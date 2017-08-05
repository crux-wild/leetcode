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

  private def getPortionOfSection
    (portion: String)(section: Section, bound: Int) {

    val sectionBound = portion match {
      case "before" => section.end
      case "after" => section.start
    }

    if (sectionBound <= bound) {
      section
    } else {
      val splitSections = new BinarySplitSection(section)
      val section = portion.match {
        case "before" => splitSections.before
        case "after" => splitSections.after
      }
      getPortionOfSection(portion, section, bound)
    }
  }

  private getTotalOfPortion(portion: String)(splitSection: Section) {
    val bound = portion match {
      case "before" => section1.end
      case "after" => section1.start
    }

    val section1 = portion match {
      case "before" => splitSection.before
      case "after" => splitSection.after
    }

    val getSection2 = getPortionOfSection(portion)
    val section2 = getSection2(portion, section1, bound)

    Section.statisticCount(Array(section1, section2))
  }

  private def recursiveAreas(
    area1: Area[T], area2: Area[T], before: Int, after: Int): Double = {
    val section1 = _area1.section
    val section2 = _area2.section

    val lazy binarySplitSection = new BinarySplitSection(section1)

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
