package com.leetcode.scala

class BinarySplitSection(section: Section) {
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
