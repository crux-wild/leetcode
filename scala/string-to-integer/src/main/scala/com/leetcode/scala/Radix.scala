package com
package leetcode
package scala

class Radix(val lexeme: String, val radix: Byte) extends Token {
  val tag = Tag.RADIX
}

object Radix extends Enumeration {
  type Radix = Value
  val OCT, HEX = Value

  def isRadix(r: Radix) = !(r == OCT || r == HEX)
}
