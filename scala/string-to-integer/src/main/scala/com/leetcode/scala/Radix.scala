package com
package leetcode
package scala

class Radix(val lexeme: String, val value: Byte)
  extends Token {

  val tag = Tag.Radix
}

object Radix {
  implicit def unit2radix(unit: Unit): Radix = new Radix("", 10)

  implicit def null2radix(anyRef: AnyRef): Radix = {
    if (anyRef == null) new Radix("", 10)
  }
}
