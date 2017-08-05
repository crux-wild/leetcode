package com.leetcode.scala

class BinarySplitSection(section: Section) {
  private lazy val _before = section / 2

  def before = _before
  def after: Section = {
    Section.diffTwoSection(section, _before)
      .getOrElse("end(+)", Nil)
  }
}
