package com.leetcode.scala

import scala.Enumeration

object SectionCompare extends Enumeration {
  type SectionCompare = Value
  val LESSER, GREATER, BE_INCLUDED, INCLUDED, EQUAL, END_INCLUDED,
    START_INCLUDED, NO_RELATIVE = Value
}
