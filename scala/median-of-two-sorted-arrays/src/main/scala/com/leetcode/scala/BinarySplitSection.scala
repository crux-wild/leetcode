package com.leetcode.scala

class BinarySplitSection(section: Section) {
  private lazy val _before = getBefore()++
  private lazy val _after = getAfter()

  def before = _before
  def after = _after

  private def isOdd(): Boolean = {
    if (section.length % 2 != 0)
      true
    else
      false
  }

  private def getBefore(): Section = {
    if (section.length <= 2)
      throw new IllegalArgumentException("Section length lesser than two, " +
        "cant't be split")
    else
      section / 2
  }

  private def getAfter(): Section = {
    Section.diffTwoSection(section, _before)
      .getOrElse("end(+)", Nil)
  }
}
