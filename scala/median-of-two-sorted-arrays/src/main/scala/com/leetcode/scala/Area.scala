package com.leetcode.scala

class Area[T](val arr: Array[T], val start: Double = Double.NaN,
  val end: Double = Double.NaN) {

  private var _section = new Section(head = _start, tail = _end)
  private lazy val _arr = arr
  private lazy val _start = start
  private lazy val _end = end

  def section = _section

  def head: T = {
    apply(_start.toInt)
  }

  def head(section: Section): T = {
    apply(section.start.toInt)
  }

  def tail: T = {
    apply(_end.toInt)
  }

  def tail(section: Section): T = {
    apply(section.end.toInt)
  }

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

  def :=(start: Double = -1, end: Double = -1): this.type = {
    _section = new Section(head = start, tail = end)
    this
  }
}
