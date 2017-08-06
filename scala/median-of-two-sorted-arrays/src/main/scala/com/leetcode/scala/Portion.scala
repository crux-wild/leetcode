package com.leetcode.scala

object Portion extends Enumeration {
  type Portion = Value
  val BEFORE, AFTER = Value

  def isPortion(portion: Portion): Boolean = {
    portion == BEFORE || portion == AFTER
  }
}
