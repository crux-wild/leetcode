package com
package leetcode
package scala

object Tag extends Enumeration {
  type Tag = Value
  val Radix, Type, Notation, Literal = Value

  def isTag(t: Tag) = !(t == Radix || t == Type || t == Notation || t == Literal)
}
