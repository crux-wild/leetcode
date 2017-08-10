package com.leetcode.scala

import _root_.scala.math
import _root_.scala.Array
import _root_.scala.collection.mutable.HashMap

class Section(val head: Double, val tail: Double) {
  private var _length = getLength()
  private var _start = head
  private var _end = tail

  def start = _start
  def end = _end
  def length = _length

  def +(plus: Double): Section = {
    val head = _start + plus
    val tail = _end + plus
    new Section(head, tail)
  }

  def -(sub: Double): Section = {
    val head = _start - sub
    val tail = _end - sub
    new Section(head, tail)
  }

  def *(multi: Double): Section = {
    val product = _length * multi
    val length = product
    val head = _start
    val tail = getEnd(start = _start , length = _length)
    new Section(head, tail)
  }

  def /(divisor: Double): Section = {
    val quotients = _length / divisor
    val length = quotients
    val head = _start
    val tail = getEnd(start = _start , length = length)
    if (head > tail)
      throw IllegalArgumentException("head shouldn't lesser than tail")
    else
      new Section(head, tail)
  }

  def /(divisor: Int): Section = {
    val quotients = math.ceil(_length / divisor)
    val length = quotients
    val head = _start
    val tail = getEnd(start = _start , length = length)
    if (head > tail)
      throw IllegalArgumentException("head shouldn't lesser than tail")
    else
      new Section(head, tail)
  }

  def ++(): this.type = {
    _end = _end + 1
    _length = _length + 1
    this
  }

  def +=(plus: Double): this.type = {
    _end = _end + plus
    _start = _start + plus
    this
  }

  def -=(sub: Double): this.type = {
    _end = _end - sub
    _start = _start - sub
    this
  }

  def *=(multi: Double): this.type = {
    val product = _length * multi
    _length = product
    val end = getEnd(start = _start , length = _length)
    if (end < _start)
      throw IllegalArgumentException("end shouldn't lesser than start")
    else
      _end = end
    this
  }

  def /=(divisor: Double): this.type = {
    val quotients = math.ceil(_length / divisor)
    _length = quotients
    _end = getEnd(start = _start , length = _length)
    this
  }

  def isNull(): Boolean = {
    if (_start.isNaN || _end.isNaN)
      true
    else
      false
  }

  private def getEnd(start: Double, length: Double): Double = {
    start + length - 1.0
  }

  private def getLength(): Double = {
    if (end < start)
      throw IllegalArgumentException(
        "Argument end shouldn't lesser than argument start")
    if (isNull())
      0
    else
      tail - head + 1.0
  }
}

object Section {
  def statisticCount(sectionArray: Array[Section]): Double = {
    var count = 0.0
    for (section <- sectionArray) {
      count = count + section.length
    }
    count
  }

  def diffTwoSection(
    section1: Section, section2: Section): HashMap[String, Section] = {
    val diffMap = new HashMap[String, Section]()

    if (section1.start >= section2.start)
      diffMap +=
        "start(+)" -> new Section(section2.start, section1.start - 1)
    else if (section1.start < section2.start)
      diffMap +=
        "start(-)" -> new Section(section1.start, section2.start - 1)

    if (section1.end >= section2.end)
      diffMap += "end(+)" -> new Section(section2.end + 1, section1.end)
    else if (section1.end < section2.end)
      diffMap += "end(-)" -> new Section(section1.end + 1, section2.end)
    diffMap
  }

  implicit def anyRef2section(ref: AnyRef): Section = {
    new Section(head = Double.NaN, tail = Double.NaN)
  }
}
