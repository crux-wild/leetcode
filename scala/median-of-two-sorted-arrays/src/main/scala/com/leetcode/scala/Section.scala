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

  def *=(multi: Int): Unit = {
    val product = _length * multi
    _end = product
  }

  def /=(divisor: Int): Unit = {
    val quotients = math.ceil(_length / divisor).toInt
    _end = quotients
  }

  def +=(plus: Int): Unit = {
    _end = _end + plus
    _start = _start + plus
  }

  def -=(sub: Int): Unit = {
    _end = _end - sub
    _start = _start - sub
  }

  private def getLength(): Int = {
    if (end < start)
      throw IllegalArgumentException(
        "Argument end shouldn't lesser than argument start")
    else
      tail - head + 1
  }
}
