package com
package leetcode
package scala

abstract class Type(val lexeme: String) extends Token {
  type T

  val tag = Tag.TYPE

  var value: T = 0.asInstanceOf[T]
}

object Type extends Enumeration {
  type Type = Value
  val LONG, INT = Value

  def isType(t: Type) = !(t == LONG || t == INT)
}
