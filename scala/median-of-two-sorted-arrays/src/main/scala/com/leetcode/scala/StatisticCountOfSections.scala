package com.leetcode.scala

class StatisticCountOfSections(portion: Portion.Value,
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
