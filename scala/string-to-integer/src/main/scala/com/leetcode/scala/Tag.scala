package com
package leetcode
package scala

object Tag extends Enumeration {
  type Tag = Value
  val RADIX, TYPE, NOTATION, LITERAL = Value

  def isTag(t: Tag) = !(t == RADIX || t == TYPE || t == NOTATION || t == LITERAL)
}
