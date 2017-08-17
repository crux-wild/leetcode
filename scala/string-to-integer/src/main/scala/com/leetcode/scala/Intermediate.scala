package com
package leetcode
package scala

class Intermediate {
  private var _prefix = new Radix(10)
  private var _digits1 = new Digits("")
  private var _infix = new Notation("")
  private var _digits2 = new Digits("")
  private var _suffix = new Long("")

  def prefix = _prefix
  def prefix_= (radix: Radix): Unit ={
    _prefix = radix
  }

  def digits1 = _digits1
  def digits1_= (digits: Digits): Unit ={
    _digits1 = digits1
  }

  def infix = _infix
  def infix_= (notation: Notation): Unit ={
    _infix = notation
  }

  def digits2 = _digits2
  def digits2_= (digits: Digits): Unit = {
    _digits2 = digits2
  }

  def suffix = _suffix
  def suffix_= (long: Long): Unit = {
    _suffix = long
  }
}
