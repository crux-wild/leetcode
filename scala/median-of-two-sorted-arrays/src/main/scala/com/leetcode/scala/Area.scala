package com.leetcode.scala

import com.leetcode.scala.Section

class Area(val index: Int = 0, val section: Section) {
  private var _length = section.start - section.end
  private var _index = index
  private var _section = section

  def length = _length
}
