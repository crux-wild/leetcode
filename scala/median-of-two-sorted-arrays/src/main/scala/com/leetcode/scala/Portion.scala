package com.leetcode.scala

object Portion extends Enumeration {
  type Portion = Value
  val BEFORE, AFTER = Value

  def checkPortion(portion: Portion): Unit = {
    if (!(portion == BEFORE || portion == AFTER))
      throw IllegalArgumentException(
        "Argument portion should be a member of Portion")
  }
}
