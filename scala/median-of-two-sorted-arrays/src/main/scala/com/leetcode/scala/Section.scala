package com.leetcode.scala

import _root_.scala.math

import scala.IllegalArgumentException

class Section(val head: Int, val tail: Int) {
  private var _length = getLength()
  private var _start = head
  private var _end = tail

  def start = _start
  def end = _end
  def length = _length

  def +(plus: Int): Section = {
    val head = _start + plus
    val tail = _end + plus
    new Section(head, tail)
  }

  def -(sub: Int): Section = {
    val head = _start - sub
    val tail = _end - sub
    new Section(head, tail)
  }

def *(multi: Int): Section = {
    val product = _length * multi
    val length = product
    val head = _start
    val tail = getEnd(start = _start , length = _length)
    new Section(head, tail)
  }

  def /(divisor: Int): Section = {
    val quotients = math.ceil(_length / divisor).toInt
    val length = quotients
    val head = _start
    val tail = getEnd(start = _start , length = _length)
    if head > tail
        "Result head shouldn't lesser than start")
    else
      new Section(head, tail)
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

  def *=(multi: Int): this.type = {
    val product = _length * multi
    _length = product
    val end = getEnd(start = _start , length = _length)
    if end < _start
        "Result end shouldn't lesser than start")
    else
      _end = end
    this
  }

  def /=(divisor: Int): this.type = {
    val quotients = math.ceil(_length / divisor).toInt
    _length = quotients
    _end = getEnd(start = _start , length = _length)
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
