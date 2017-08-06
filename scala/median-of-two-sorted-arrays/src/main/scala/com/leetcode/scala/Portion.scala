package com.leetcode.scala

object Portion extends Enumeration {
  type Portion = Value
  val Before, After = Value

  def isPortion(portion: Portion): Boolean = {
    portion == Before || portion == After
  }
}
}
