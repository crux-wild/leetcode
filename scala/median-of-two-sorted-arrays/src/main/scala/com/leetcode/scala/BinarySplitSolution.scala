package com.leetcode.scala

import _root_.scala.collection.mutable.ListBuffer
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

    if (_medianValue.isFinished == true)
      processNoneMedian()
    else if ((count == _medianValue.surplus) || (_flag == true && count == 1))
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

    val section2Clip = new BinarySearchSection[T](portion, _area2.section,
      section1Clips, _area1, _area2).section
    val countOfBranch = new StatisticCountOfSections(portion, section2Clip,
      section1Clips).count
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

    if ((section1.length <= 2) && (section2.length <= 2))
      throw new IllegalArgumentException(
        "Both area1 and area2 can't be indivisible")
    else if (section2.length <= 2)
      section1
    else if (section1.length <= 2)
      switchArea()
      _area1.section
  }


  private def appendValue[T](i: Int, index: Int): Unit = {
    if (i == 1)
      _medianValue += _area2.apply(index)
    else
      _medianValue += _area1.apply(index)
  }

  private def prcoessIndivisibleAreas(before: Int, after: Int): Unit = {
    var listBuffer = new ListBuffer[T]()
    val start1 = _area1.section.start.toInt
    val end1 = _area1.section.end.toInt
    for (index <- start1 to end1) {
      listBuffer += area1.apply(index.toInt)
    }
    val start2 = _area2.section.start.toInt
    val end2 = _area2.section.end.toInt
    for (index <- start2 to end2) {
      listBuffer += area2.apply(index.toInt)
    }
    listBuffer = listBuffer.sortWith((A, B) => A.asInstanceOf[Double] < B.asInstanceOf[Double])
    for (index <- (before)  to (before + _medianValue.surplus - 1)) {
      _medianValue += listBuffer.apply(index)
    }
  }

  private def recursiveControlFlow(before: Int, after: Int): Unit = {

    var section: Section = Nil
    try {
      section = getDivisibleSection()
    } catch {
      // area长度为1的情况无法进行二分
      // 如果执行到这步骤说明两个area长度都为2或者1
      case exception:IllegalArgumentException => {
        return prcoessIndivisibleAreas(before, after)
      }
    }
    val section1Clips = new BinarySplitSection(_area1.section)
    val condition = processBranch(Portion.BEFORE, section1Clips, before)
    println(condition)
    if ((condition == Condition.SPLIT_MEDIAN) ||
      (condition == Condition.NONE_MEDIAN) ||
      (_flag == true && condition ==  Condition.RESOLVED_MEDIAN))
      processBranch(Portion.AFTER , section1Clips, after)
  }
}
