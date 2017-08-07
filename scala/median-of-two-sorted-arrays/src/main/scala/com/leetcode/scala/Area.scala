package com.leetcode.scala

import scala.IllegalArgumentException

class Area[T](val arr: Array[T], val start: Int = -1, val end: Int = -1) {
  private val _arr = arr
  private val _length = _arr.length
  private val _start = if (start == -1) 0 else start
  private val _end = if (end == -1) _arr.length - 1 else end

  private var _section = new Section(head = _start, tail = _end)

  def section = _section

  def apply(index: Int): T = {
    if (!(index >= _start && index <= _end))
      throw IllegalArgumentException(
        "Index should within the interval [start, end]")
    else
      arr.apply(index)
  }

  def :=(section: Section): this.type = {
    _section = section
    this
  }

  def :=(start: Int = -1, end: Int = -1): this.type = {
    _section = new Section(head = start, tail = end)
    this
  }
}
