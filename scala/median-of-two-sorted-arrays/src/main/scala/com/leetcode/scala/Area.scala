package com.leetcode.scala

import com.leetcode.scala.Section

class Area(val index: Int = 0, val start: Int, val end: Int, val count: Int) {
  private var _count = count
  private var _index = index
  private var _section = new Section(start, end)

  def section = _section
}
