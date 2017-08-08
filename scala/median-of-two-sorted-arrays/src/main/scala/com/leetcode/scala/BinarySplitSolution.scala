package com.leetcode.scala

import _root_.scala.Array
import _root_.scala.math

class BinarySplitSolution[T](area1: Area[T], area2: Area[T],
  before: Int, after: Int) {

  private var _area1 = area1
  private var _area2 = area2
  private val _total = getTotal()
  private val _medianValue = new MedianValueStateMachine[T](_total)
  private val _medianCount = _medianValue.total
  private val _before = before
  private val _after = after
  private var _flag = false
  private val _median = getMedian()

  def median = _median

  private def getTotal(): Int = {
    val section1 = _area1.section
    val section2 = _area2.section
    Section.statisticCount(Array(section1, section2)).toInt
  }

  private def getMedian(): Int = {
    recursiveControlFlow(_area1, _area2, _before, _after)
    _medianValue.median.toInt
  }

  private def processSplitMedian(
    section1: Section, section2: Section, base: Int): Condition.Value = {

    println("--------split---------")
    val total = Section.statisticCount(Array(section1, section2)).toInt
    _flag = true
    area1 := section1
    area2 := section2
    recursiveControlFlow(area1, area2, before, total - base - 1)
    Condition.SPLIT_MEDIAN
  }

  private def processResolvedMedian(
    section1: Section, section2: Section): Condition.Value = {

    println("--------resolve---------")
    area1 := section1
    area2 := section2

    val start1 = area1.section.start.toInt
    val end1 = area1.section.end.toInt
    for (index <- start1 to end1) {
      _medianValue += area1.apply(index)
    }

    val start2 = area2.section.start.toInt
    val end2 = area2.section.end.toInt
    for (index <- start2 to end2) {
      _medianValue += area1.apply(index)
    }
    Condition.RESOLVED_MEDIAN
  }

  private def processContainMedian(
    section1: Section, section2: Section, base: Int): Condition.Value = {

    println("--------contain---------")
    val total = Section.statisticCount(Array(section1, section2)).toInt
    area1 := section1
    area2 := section2
    recursiveControlFlow(area1, area2, before, total - base)
    Condition CONTAIN_MEDIAN
  }

  private def processNoneMedian(): Condition.Value = {
    println("--------none---------")
    Condition.NONE_MEDIAN
  }

  private def controlFlowOfBranch(
    count: Int, base: Int, section1: Section, section2: Section
    ): Condition.Value = {

    val baseMedian = base + _medianCount
    val baseOne = base + 1

    if ((count == _medianCount) || (_flag == true && count == 1))
      processResolvedMedian(section1, section2)
    else if ((_medianCount == 2) && (count == baseOne))
      processSplitMedian(section1, section2, base)
    else if (count >= baseMedian)
      processContainMedian(section1, section2, base)
    else
      processNoneMedian()
  }

  private def processBranch(portion: Portion.Value,
    section1Clips: BinarySplitSection, base: Int): Condition.Value = {

    val section2Clip =
      new BinarySearchSection(portion, _area2.section, section1Clips).section
    val countOfBranch =
      new StatisticCountOfSections(portion, section2Clip, section1Clips).count

    controlFlowOfBranch(
      countOfBranch, base, section1Clips.before, section2Clip)
  }

  private def switchArea(): Unit = {
    val tmp = _area2
    _area2 = _area1
    _area1 = tmp
  }

  private def getDivisibleSection(area1: Area[T], area2: Area[T]): Section = {
    lazy val section1 = area1.section
    lazy val section2 = area1.section

    if ((section1.length == 1) && (section2.length == 1)) {
      throw new IllegalArgumentException(
        "Both area1 and area2 can't be indivisible")
    }
    else if (section1.length == 1) {
      switchArea()
      section2
    }
    else {
      section1
    }
  }

  private def prcoessIndivisibleAreas(area1: Area[T], area2: Area[T],
    before: Int, after: Int): Unit = {

    val index1 = area1.section.start.toInt
    val index2 = area2.section.start.toInt
    val elem1 = area1.apply(index1)
    val elem2 = area1.apply(index2)

    if (_medianCount == 2) {
      _medianValue += elem1
      _medianValue += elem2
    } else if (_medianCount == 1) {
      if (before == 1) {
        _medianValue += math.max(
          elem1.asInstanceOf[Double], elem2.asInstanceOf[Double]).asInstanceOf[T]
      } else if (after ==  1) {
        _medianValue += math.min(
          elem1.asInstanceOf[Double], elem2.asInstanceOf[Double]).asInstanceOf[T]
      }
    }
  }

  private def recursiveControlFlow(area1: Area[T], area2: Area[T],
    before: Int, after: Int): Unit = {

    var section: Section = Nil
    try {
      section = getDivisibleSection(area1, area2)
    } catch {
      // 如果执行到这步骤说明两个area长度都为1
      case exception:IllegalArgumentException => {
        return prcoessIndivisibleAreas(area1, area2, before, after)
      }
    }

    val sectionClips = new BinarySplitSection(area1.section)
    println("before: " + sectionClips.before.start + " : " + sectionClips.before.end)
    println("after: " + sectionClips.after.start + " : " + sectionClips.after.end)
    val condition = processBranch(Portion.BEFORE, sectionClips, before)
    println("condition: " + condition)
    if ((condition == Condition.SPLIT_MEDIAN) ||
      (condition == Condition.NONE_MEDIAN) ||
      (_flag == true && condition ==  Condition.RESOLVED_MEDIAN))
      processBranch(Portion.AFTER , sectionClips, after)
  }
}

private class BinarySplitSection(section: Section) {
  private lazy val _before = getBefore()
  private lazy val _after = getAfter()

  def before = _before
  def after = _after

  private def getBefore(): Section = {
    if (section.end < section.start + 1)
      throw new IllegalArgumentException("Section length lesser than one, " +
        "cant't be split")
    else
      section / 2
  }

  private def getAfter(): Section = {
    Section.diffTwoSection(section, _before)
      .getOrElse("end(+)", Nil)
  }
}

private class BinarySearchSection(portion: Portion.Value, section2: Section,
  sectionClips: BinarySplitSection) {

  private val _sectionClips = sectionClips
  private val _portion = getPortion()
  private val _bound = getBound()
  private var _section = getSection(section2)

  def section = _section

  private def getBound(): Int = {
    val bound = _portion match {
      case Portion.BEFORE => sectionClips.before.end
      case Portion.AFTER => sectionClips.after.start
    }
    bound.toInt
  }

  private def getPortion(): Portion.Value = {
    Portion.checkPortion(portion)
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

private class StatisticCountOfSections(portion: Portion.Value,
  section2: Section, section1Clips : BinarySplitSection) {

  private val _section1Clips = section1Clips
  private val _portion = getPortion()
  private var _count = getCount()

  def count = _count

  private def getPortion(): Portion.Value = {
    Portion.checkPortion(portion)
    portion
  }

  private def getCount(): Int = {
    val section1 = _portion match {
      case Portion.BEFORE =>  _section1Clips.before
      case Portion.AFTER => _section1Clips.after
    }
    Section.statisticCount(Array(section1, section2)).toInt
  }
}
