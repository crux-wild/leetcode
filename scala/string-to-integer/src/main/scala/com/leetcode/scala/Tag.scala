package com
package leetcode
package scala

/**
 * 词法单元名枚举类
 */
object Tag extends Enumeration {
  type Tag = Value
  val Radix, Digits, Notation, Long, Whole = Value
}
