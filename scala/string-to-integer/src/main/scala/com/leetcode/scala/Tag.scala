package com
package leetcode
package scala

object Tag extends Enumeration {
  type Tag = Value
  val RADIX, BYTES = Value

  def isTag(t: Tag) = !(t == RADIX || t == BYTES)
}
