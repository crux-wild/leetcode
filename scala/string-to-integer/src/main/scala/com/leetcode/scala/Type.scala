package com
package leetcode
package scala

class Type(val lexeme: String) extends Token {
  val tag = Tag.Type
}

object Type {
  implicit def unit2type(unit: Unit): Type = new Type("")

  implicit def null2type(anyRef: AnyRef): Type = {
    if (anyRef == null) new Type("")
  }
}
