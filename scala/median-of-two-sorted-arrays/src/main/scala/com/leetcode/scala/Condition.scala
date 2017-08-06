package com.leetcode.scala

object Condition extends Enumeration {
  type Condition = Value
  val SPLIT_MEDIAN, CONTAIN_MEDIAN, RESOLVE_MEDIAN, NONE_MEIDAN = Value

  def isCondition(condition: Condition): Boolean = {
    condition == SPLIT_MEDIAN || condition ==  CONTAIN_MEDIAN ||
      condition == RESOLVE_MEDIAN || condition == NONE_MEIDAN
  }
}
