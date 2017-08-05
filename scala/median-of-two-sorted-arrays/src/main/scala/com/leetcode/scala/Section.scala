package com.leetcode.scala

import _root_.scala.math

import scala.IllegalArgumentException

class Section(val head: Int, val tail: Int) {
  private val _length = getLength()
  private var _start = head
  private var _end = tail

  def start = _start
  def end = _end
  def length = _length

  def *=(multi: Int): this.type = {
    val product = _length * multi
    _length = product
    _end = getEnd(start = _start , length = _length)
    this
  }

  def /=(divisor: Int): this.type = {
    val quotients = math.ceil(_length / divisor).toInt
    _length = quotients
    _end = getEnd(start = _start , length = _length)
    this
  }

  def +=(plus: Int): this.type = {
    _end = _end + plus
    _start = _start + plus
    this
  }

  def -=(sub: Int): this.type = {
    _end = _end - sub
    _start = _start - sub
    this
  }

  private def getEnd(start: Int, length: Int): Int = {
    start + length - 1
  }

  private def getLength(): Int = {
    if (end < start)
      throw IllegalArgumentException(
        "Argument end shouldn't lesser than argument start")
    else
      tail - head + 1
  }
}
