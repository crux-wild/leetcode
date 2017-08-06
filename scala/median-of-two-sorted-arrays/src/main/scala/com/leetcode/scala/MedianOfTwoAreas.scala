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

  private class BinarySearchSection
    (portion: Portion)(section: Section, bound: Int) {

    private val _bound = bound
    private val _portion = getPortion()
    private var _section = getSection(section)

    def section = _section

    private def getPortion(): Portion = {
      if (!(Portion.isPortion(portion)))
        throw IllegalArgumentException(
          "Argument portion should be a member of Portion")
      else
        portion
    }

    private def getSectionOfPortion(section: Section): Section = {
      val clipsOfSection = new BinarySplitSection(section)
      _portion.match {
        case Before => clipsOfSection.before
        case After => clipsOfSection.after
      }
    }

    private def getSection(section: Section): Section = {
      val sectionBound = _portion match {
        case Before => section.end
        case After => section.start
      }
      if sectionBound <= bound)
        section
      else
        section = getSectionOfPortion(section)
        getSection(section)
    }
  }

  private class StatisticSectionsOfPortion(portion: Portion)(section2: Section,
    clipsOfSection1 : BinarySplitSection) {

    private val _clipsOfSection1 = clipsOfSection1
    private val _portion = getPortion()
    private var _count = getCount()

    def count = _count

    private def getPortion(): Portion = {
      if (!(Portion.isPortion(portion)))
        throw IllegalArgumentException(
          "Argument portion should be a member of Portion")
      else
        portion
    }

    private def getCount(): Int = {
      val section1 = _portion match {
        case Before =>  _clipsOfSection1.before
        case After => _clipsOfSection1.after
      }
      Section.statisticCount(Array(section1, section2))
    }
  }

  /** Need reconsitution start **/
  private def updateSections(portion: String)(total: Int) = {
    val bound = portion {
      case "before" => after + 1
      case "after" => before + 1
    }

    if (_medianValue.total == 2 && total >= bound)
      _flag = true
    else if (
      (_isSplitMedian == true && beforeTotal == 1) ||
      (beforeTotal == _medianOfCount))
      // @TODO
    else if (beforeTotal >= total + _medianValue.total)
      recursiveAreas(
        area1 = section1Before, area2 = section2Before, before,
        after = after - afterTotal)
  }

  private def recursiveAreas(
    area1: Area[T], area2: Area[T], before: Int, after: Int): Double = {
    val section1 = _area1.section
    val section2 = _area2.section

    val lazy binarySplitSection = new BinarySplitSection(section1)
  }
  /** Need reconsitution end **/

  private getMedian(): T = {
    val median = new Median(start = 1, end = _total)
    val before = median.one - 1
    val after = _total - median.two

    recursiveAreas(_area1, _area2, before, after)
  }
}
