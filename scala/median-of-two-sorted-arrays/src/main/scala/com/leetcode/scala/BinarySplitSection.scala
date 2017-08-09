package com.leetcode.scala

class BinarySplitSection(section: Section, disjoint: Boolean = false) {
  private lazy val _before = if (disjoint == true) getBefore()++ else getBefore()
  private lazy val _after = getAfter()

  def before = _before
  def after = _after

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
