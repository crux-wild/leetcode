package com
package leetcode
package scala

class Digits(val lexeme: String) extends Token {
  val tag: Tag.Value = Tag.Literal
}

object Digits {
  implicit def unit2digits(unit: Unit): Digits = new Digits("")

  implicit def null2digits(anyRef: AnyRef): Digits = {
    if (anyRef == null) new Digits("")
  }
}
