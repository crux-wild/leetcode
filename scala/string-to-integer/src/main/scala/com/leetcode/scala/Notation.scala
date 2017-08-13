package com
package leetcode
package scala

class Notation(val lexeme: String) extends Token {
  val tag = Tag.NOTATION
}

object Notation {
  implicit def unit2notaion(unit: Unit): Notation = new Notation("")
}
