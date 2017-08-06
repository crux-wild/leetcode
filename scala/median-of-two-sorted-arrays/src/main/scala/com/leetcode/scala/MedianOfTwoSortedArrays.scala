package com.leetcode.scala

import _root_.scala.Array

class MedianOfTwoSortedArrays[T](val arr1: Array[T], val arr2: Array[T]) {
  private val _arr1 = arr1
  private val _arr2 = arr2
  private val _len1 = arr1.length
  private val _len2 = arr2.length
  private val _area1 = new Area(arr = arr1, start = 0, end = _len1)
  private val _area2 = new Area(arr = arr2, start = 0, end = _len2)
  private val _total = getTotal()
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
  (portion: Portion.Value, section2: Section, sectionClips: BinarySplitSection) {

    private val _sectionClips = sectionClips
    private val _bound = getBound()
    private val _portion = getPortion()
    private var _section = getSection(section2)

    def section = _section

    private def getBound(): Int = {
      _portion match {
        case Portion.BEFORE => _section.end
        case Portion.AFTER => _section.start
      }
    }

    private def getPortion(): Portion.Value = {
      if (!(Portion.isPortion(portion)))
        throw IllegalArgumentException(
          "Argument portion should be a member of Portion")
      else
        portion
    }

    private def getSectionOfPortion(section: Section): Section = {
      val sectionClips = new BinarySplitSection(section)
      _portion match {
        case Portion.BEFORE => sectionClips.before
        case Portion.AFTER => sectionClips.after
      }
    }

    private def getSection(section: Section): Section = {
      val sectionBound = _portion match {
        case Portion.BEFORE => section.end
        case Portion.AFTER => section.start
      }
      if (sectionBound <= _bound) {
        section
      } else {
        val sectionOfPortion = getSectionOfPortion(section)
        getSection(sectionOfPortion)
      }
    }
  }

  private class StatisticCountOfSections(portion: Portion.Value, section2: Section,
    section1Clips : BinarySplitSection) {

    private val _section1Clips = section1Clips
    private val _portion = getPortion()
    private var _count = getCount()

    def count = _count

    private def getPortion(): Portion.Value = {
      if (!(Portion.isPortion(portion)))
        throw IllegalArgumentException(
          "Argument portion should be a member of Portion")
      else
        portion
    }

    private def getCount(): Int = {
      val section1 = _portion match {
        case Portion.BEFORE =>  _section1Clips.before
        case Portion.AFTER => _section1Clips.after
      }
      Section.statisticCount(Array(section1, section2))
    }
  }

  private class BinarySplitSolve(area1: Area[T], area2: Area[T],
    before: Int, after: Int) {

    private val _medianValue = new MedianValueStateMachine[T](_total)
    private val _medianCount = _medianValue.total
    private val _area1 = area1
    private val _area2 = area2
    private val _before = before
    private val _after = after
    private var _flag = false
    private val _median = getMedian()

    def median = _median

    private def getMedian(): T = {
      recursiveControlFlow(_area1, _area2, _before, _after)
      _medianValue.median
    }

    private def processSplitMedian(
      section1: Section, section2: Section, base: Int): Condition.Value = {

      val total = Section.statisticCount(Array(section1, section2))
      _flag = true
      area1 := section1
      area2 := section2
      recursiveControlFlow(area1, area2, before, total - base - 1)
      Condition.SPLIT_MEDIAN
    }

    private def processResolvedMedian(
      section1: Section, section2: Section): Condition.Value = {
      area1 := section1
      area2 := section2
      for (index <- area1.section.start to area1.section.end) {
        _medianValue += area1.apply(index)
      }
      for (index <- area2.section.start to area2.section.end) {
        _medianValue += area1.apply(index)
      }
      Condition.RESOLVED_MEDIAN
    }

    private def processContainMedian(
      section1: Section, section2: Section, base: Int): Condition.Value = {

      val total = Section.statisticCount(Array(section1, section2))
      area1 := section1
      area2 := section2
      recursiveControlFlow(area1, area2, before, total - base)
      Condition CONTAIN_MEDIAN
    }

    private def processNoneMedian(): Condition.Value = {
      Condition.NONE_MEDIAN
    }

    private def controlFlowOfBranch(
      count: Int, base: Int, section1: Section, section2: Section
      ): Condition.Value = {

      val baseMedian = base + _medianCount
      val baseOne = base + 1

      if ((count == baseMedian) || (_flag == true && count == baseOne))
        processResolvedMedian(section1, section2)
      else if ((_medianCount == 2) && (count == baseOne))
        processSplitMedian(section1, section2, base)
      else if (count > baseMedian)
        processContainMedian(section1, section2, base)
      else
        processNoneMedian()
    }

    private def processBranch(portion: Portion.Value,
      section1Clips: BinarySplitSection, base: Int): Condition.Value = {

      val section2Clip =
        new BinarySearchSection(portion, _area2.section, section1Clips)
      val countOfBranch =
        new StatisticCountOfSections(portion, section2Clip, section1Clips).count

      controlFlowOfBranch(
        countOfBranch, base, section1Clips.before, section2Clip)
    }

    private def recursiveControlFlow(area1: Area[T], area2: Area[T], before: Int,
      after: Int): Unit = {

      val section1 = area1.section
      val section1Clips = new BinarySplitSection(section1)

      val condition = processBranch(Portion.BEFORE, section1Clips, before)

      if ((condition == Condition.SPLIT_MEDIAN) ||
        (condition == Condition.NONE_MEDIAN) ||
        (_flag == true && condition ==  Condition.RESOLVED_MEDIAN))
        processBranch(Portion.AFTER , section1Clips, after)
    }
  }

  private def getTotal(): Int = {
    val section1 = _area1.section
    val section2 = _area2.section
    Section.statisticCount(Array(section1, section2))
  }

  private def getMedian(): T = {
    val median = new Median(start = 1, end = _total)
    val before = median.one - 1
    val after = _total - median.two

    new BinarySplitSolve(_area1, _area2, before, after).median
  }
}
