package com.leetcode.scala

import _root_.scala.Array
import _root_.scala.math

class BinarySplitSolution[T](area1: Area[T], area2: Area[T],
  before: Int, after: Int) {

  private var _area1 = area1
  private var _area2 = area2
  private val _total = getTotal()
  private val _medianValue = new MedianValueStateMachine[T](_total)
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

  private def getMedian(): Double = {
    recursiveControlFlow(_before, _after)
    _medianValue.median
  }

  private def processSplitMedian(
    section1: Section, section2: Section, base: Int): Condition.Value = {

    val total = Section.statisticCount(Array(section1, section2)).toInt
    _flag = true
    _area1 := section1
    _area2 := section2
    recursiveControlFlow(before, total - base - 1)
    Condition.SPLIT_MEDIAN
  }

  private def processResolvedMedian(
    section1: Section, section2: Section): Condition.Value = {

    _area1 := section1
    _area2 := section2

    val start1 = _area1.section.start.toInt
    val end1 = _area1.section.end.toInt
    for (index <- start1 to end1) {
      _medianValue += _area1.apply(index)
    }
    val start2 = _area2.section.start.toInt
    val end2 = _area2.section.end.toInt
    for (index <- start2 to end2) {
      _medianValue += _area2.apply(index)
    }
    Condition.RESOLVED_MEDIAN
  }

  private def processContainMedian(
    section1: Section, section2: Section, base: Int): Condition.Value = {

    val total = Section.statisticCount(Array(section1, section2)).toInt
    _area1 := section1
    _area2 := section2
    recursiveControlFlow(before, total - base)
    Condition CONTAIN_MEDIAN
  }

  private def processNoneMedian(): Condition.Value = {
    Condition.NONE_MEDIAN
  }

  private def controlFlowOfBranch(
    count: Int, base: Int, section1: Section, section2: Section
    ): Condition.Value = {

    val baseMedian = base + _medianValue.surplus
    val baseOne = base + 1

    if ((count == _medianValue.surplus) || (_flag == true && count == 1))
      processResolvedMedian(section1, section2)
    else if ((_medianValue.surplus == 2) && (count == baseOne))
      processSplitMedian(section1, section2, base)
    else if (count >= baseMedian)
      processContainMedian(section1, section2, base)
    else
      processNoneMedian()
  }

  private def processBranch(portion: Portion.Value,
    section1Clips: BinarySplitSection, base: Int): Condition.Value = {

    val section2Clip =
      new BinarySearchSection[T](portion, _area2.section, section1Clips,
        _area1, _area2).section
    val countOfBranch =
      new StatisticCountOfSections(portion, section2Clip, section1Clips).count
    val section1 = portion match {
      case Portion.BEFORE => section1Clips.before
      case Portion.AFTER => section1Clips.after
    }

    controlFlowOfBranch(
      countOfBranch, base, section1, section2Clip)
  }

  private def switchArea(): Unit = {
    val swap = _area2
    _area2 = _area1
    _area1 = swap
  }

  private def getDivisibleSection(): Section = {
    lazy val section1 = _area1.section
    lazy val section2 = _area2.section

    if ((section1.length == 1) && (section2.length == 1)) {
      throw new IllegalArgumentException(
        "Both area1 and area2 can't be indivisible")
    }
    else if (section1.length == 1) {
      switchArea()
      _area1.section
    }
    else {
      section1
    }
  }

  private def prcoessIndivisibleAreas(before: Int, after: Int): Unit = {

    val elem1 = _area1.head
    val elem2 = _area2.head

    if (_medianValue.surplus == 2) {
      _medianValue += elem1
      _medianValue += elem2
    } else if (_medianValue.surplus == 1) {
      if (before == 1) {
        _medianValue += math.max(
          elem1.asInstanceOf[Double], elem2.asInstanceOf[Double]).asInstanceOf[T]
      } else if (after ==  1) {
        _medianValue += math.min(
          elem1.asInstanceOf[Double], elem2.asInstanceOf[Double]).asInstanceOf[T]
      }
    }
  }

  private def appendValue[T](i: Int, index: Int): Unit = {
    if (i == 1)
      _medianValue += _area2.apply(index)
    else
      _medianValue += _area1.apply(index)
  }

  private def processSectionInTheGap(
    sectionClips: BinarySplitSection): Boolean = {

    val beforeSection = sectionClips.before
    val afterSection = sectionClips.after
    // 这个方法处理的事区间没有重复的情况
    if (!(
      (_area2.head.asInstanceOf[Double] > _area1.head(beforeSection).asInstanceOf[Double]) &&
      (_area2.tail.asInstanceOf[Double] < _area1.head(afterSection).asInstanceOf[Double]))
    ) {
      return false
    }

    val sectionArray = Array(beforeSection, _area2.section, afterSection)
    var before = _before
    var flag = false
    for (index <- 0 to 2) {
      val section =  sectionArray.apply(index)
      if (_medianValue.surplus > 0)
        if ((_medianValue.surplus == 2) &&
          (section.length >= before + _medianValue.surplus))
          appendValue(index, (before + _medianValue.surplus) - 1)
        else if (section.length >= before + 1)
          appendValue(index, (before + 1) - 1)
        else
          before = before - section.length.toInt
    }
    _medianValue.isFinished == true
  }

  private def recursiveControlFlow(before: Int, after: Int): Unit = {

    var section: Section = Nil
    try {
      section = getDivisibleSection()
    } catch {
      // area长度为1的情况无法进行二分
      // 如果执行到这步骤说明两个area长度都为1
      case exception:IllegalArgumentException => {
        return prcoessIndivisibleAreas(before, after)
      }
    }

    val section1Clips = new BinarySplitSection(_area1.section)
    // section1落在section1before和section2after之间可以在线性时间内得出结果
    if (processSectionInTheGap(section1Clips)) return

    val condition = processBranch(Portion.BEFORE, section1Clips, before)
    if ((condition == Condition.SPLIT_MEDIAN) ||
      (condition == Condition.NONE_MEDIAN) ||
      (_flag == true && condition ==  Condition.RESOLVED_MEDIAN))
      processBranch(Portion.AFTER , section1Clips, after)
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

private class BinarySearchSection[T](portion: Portion.Value, section2: Section,
  sectionClips: BinarySplitSection, area1: Area[T], area2: Area[T]) {

  private val _sectionClips = sectionClips
  private val _portion = getPortion()
  private val _bound = getBound()
  private var _section = getSection(section2)

  def section = _section

  private def getBound(): Double = {
    val beforeSection = sectionClips.before
    val afterSection = sectionClips.after
    _portion match {
      case Portion.BEFORE => area1.tail(beforeSection).asInstanceOf[Double]
      case Portion.AFTER => area1.tail(afterSection).asInstanceOf[Double]
    }
  }

  private def getPortion(): Portion.Value = {
    Portion.checkPortion(portion)
    portion
  }

  private def getSectionOfPortion(section: Section): Section = {
    var sectionClips = new BinarySplitSection(section)
    _portion match {
      case Portion.BEFORE => sectionClips.before
      case Portion.AFTER => sectionClips.after
    }
  }

  private def checkBound(sectionBound: Double): Boolean = {
    if ((_portion == Portion.BEFORE && sectionBound <= _bound) ||
      (_portion == Portion.AFTER && sectionBound >= _bound))
      true
    else
      false
  }

  private def getSection(section: Section): Section = {
    var sectionBound = _portion match {
      case Portion.BEFORE => area2.tail(section).asInstanceOf[Double]
      case Portion.AFTER => area2.head(section).asInstanceOf[Double]
    }
    if (checkBound(sectionBound))
      section
    else
      try {
        val sectionOfPortion = getSectionOfPortion(section)
        return getSection(sectionOfPortion)
      } catch {
        // 发生不可分割情况
        case exception:IllegalArgumentException => {
          return new Section(Double.NaN, Double.NaN)
        }
      }
  }
}

