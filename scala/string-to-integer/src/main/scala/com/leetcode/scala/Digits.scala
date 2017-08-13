package com
package leetcode
package scala

class Digits(val lexeme: String) extends Token {
  val tag: Tag.Value = Tag.DIGITS
}

object Digits {
  implicit def unit2digits(unit: Unit): Digits = new Digits("")
}
