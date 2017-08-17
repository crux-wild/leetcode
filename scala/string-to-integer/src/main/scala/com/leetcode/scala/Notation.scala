package com
package leetcode
package scala

class Notation(val lexeme: String) extends Token {
  val tag = Tag.Notation
}

object Notation {
  implicit def null2notation(anyRef : AnyRef): Notation = {
    if (anyRef == null) new Notation("")
  }

  implicit def unit2notation(unit: Unit): Notation = new Notation("")
}
