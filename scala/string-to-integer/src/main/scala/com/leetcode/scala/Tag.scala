package com
package leetcode
package scala

object Tag extends Enumeration {
  type Tag = Value
  val RADIX, TYPE, NOTATION = Value

  def isTag(t: Tag) = !(t == RADIX || t == TYPE || t == NOTATION)
}
