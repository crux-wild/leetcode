package com
package leetcode
package scala

abstract class Type extends Token {
  type T

  val tag = Tag.TYPE

  var value: T = 0.asInstanceOf[T]
}

object Type {
  implicit def unit2type(unit: Unit): Type = new Type{
                                                      type T = Int
                                                       val lexeme = ""
                                                     }
}
