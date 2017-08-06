package com.leetcode.scala

import _root_.scala.Array

class MedianOfTwoSortedArrays[T](val arr1: Array[T], val arr2: Array[T]) {
  private val _arr1 = arr1
  private val _arr2 = arr2
  private val _area1 = new Area(arr = arr1, start = 0, end = _len1)
  private val _area2 = new Area(arr = arr2, start = 0, end = _len2)
  private val total = getTotal()
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
  (portion: Portion, section: Section, sectionClips: BinarySplitSection) {

    private val _sectionClips = sectionClips
    private val _bound = getBound()
    private val _portion = getPortion()
    private var _section = getSection(section)

    def section = _section

    private def getBound(): Int = {
      _portion.match {
        case Before => _sectionClips.end
        case After => _sectionClips.start
      }
    }

    private def getPortion(): Portion = {
      if (!(Portion.isPortion(portion)))
        throw IllegalArgumentException(
          "Argument portion should be a member of Portion")
      else
        portion
    }

    private def getSectionOfPortion(section: Section): Section = {
      val sectionClips = new BinarySplitSection(section)
      _portion.match {
        case Before => sectionClips.before
        case After => sectionClips.after
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

  private class StatisticCountOfSections(portion: Portion, section2: Section,
    section1Clips : BinarySplitSection) {

    private val _section1Clips = section1Clips
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
        case Before =>  _section1Clips.before
        case After => _section1Clips.after
      }
      Section.statisticCount(Array(section1, section2))
    }
  }

  private class BinarySplitSolve(area1: Area[T], area2: Area[T],
    before: Int, after: Int) {

    private val _medianValue = new MedianValueStateMachine(_total)
    private val _area1 = area1
    private val _area2 = area2
    private val _before = before
    private val _after = after
    private var _flag = false
    private val _median = getMedian()

    def median = _median

    private def getMedian(): T = {
      recursiveControlFlow(_area1, _area2, _before, _after)
    }

    private def processSplitMedian(): Conditon = {
        Condition.SPLIT_MEDIAN
    }

    private def processResolvedMedian(): Condition = {
        Condition.RESOLVE_MEDIAN
    }

    private def processContainMedian(): Condition = {
        Condition CONTAIN_MEDIAN
    }

    private def processNoneMedian(): Condition = {
        Condition.NONE_MEIDAN
    }

    private def controlFlowOfBranch(count: Int, base: Int): Condition = {
      if ((medianValue.count == 2) && (count == base + 1))
        processSplitMedian()
      else if ((count == _medianValue.count + base) ||
        (_flag == true && count == base + 1))
        processResolvedMedian()
      else if (count > _medianValue.count + base)
        processContainMedian()
      else
        processNoneMedian()
    }

    private def processBranch(before: Int, after: Int)
      (portion: Portion, section1Clips: BinarySplitSection): Conditon = {

      val baseOfBranch = portion match {
        Before => before
        After => after
      }

      val section2Clip =
        new BinarySearchSection(portion, section1Clips)
      val countOfBranch =
        new StatisticCountOfSections(portion, section2Clip, section1Clips)

      controlFlowOfBranch(countOfBranch, baseOfBranch)
    }

    private def recursiveControlFlow(area1: Area, area2: Area, before: Int,
      after: Int): Unit = {

      val section1 = area1.section
      val section1Clips = new BinarySplitSection(section1)

      processBranch(Portion.Before, section1Clips)
    }
  }

  private def getTotal(): Int = {
    val section1 = _area1.section
    val section2 = _area2.section
    total = Section.statisticCount(Array(section1, section2))
  }

  private getMedian(): T = {
    val median = new Median(start = 1, end = total)
    val before = median.one - 1
    val after = _total - median.two

    new BinarySplitSolve(_area1, _area2, before, after).median
  }
}
