package com
package leetcode
package scala

class Notation(val lexeme: String, val notation: Notation.Value) extends Token {
  val tag = Tag.NOTATION
}

object Notation extends Enumeration {
  type Notation = Value
  val E, NONE = Value

  def isNotation(n: Notation) = !(n == E || n == NONE)
}
