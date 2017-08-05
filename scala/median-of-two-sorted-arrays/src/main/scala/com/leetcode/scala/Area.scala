package com.leetcode.scala

import com.leetcode.scala.Section

class Area(val reference: AnyRef, val start: Int, val end: Int) {
  private val _ref = reference
  private val _section = new Section(head = start, tail = end)

  def ref = _ref
  def section = _section
}
