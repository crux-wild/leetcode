package com.leetcode.scala

object Condition extends Enumeration {
  type Condition = Value
  val SPLIT_MEDIAN, CONTAIN_MEDIAN, RESOLVED_MEDIAN, NONE_MEDIAN = Value

  def isCondition(condition: Condition): Boolean = {
    condition == SPLIT_MEDIAN || condition ==  CONTAIN_MEDIAN ||
      condition == RESOLVED_MEDIAN || condition == NONE_MEDIAN
  }
}
