package com.leetcode.scala

class BinarySearchSection[T](portion: Portion.Value, section2: Section,
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
      case Portion.AFTER => area1.head(afterSection).asInstanceOf[Double]
    }
  }

  private def getPortion(): Portion.Value = {
    Portion.checkPortion(portion)
    portion
  }

  private def getSectionOfPortion(section: Section): Section = {
    var sectionClips = new BinarySplitSection(section, false)
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
