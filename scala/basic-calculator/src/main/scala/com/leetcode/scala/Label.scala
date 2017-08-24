package com
package leetcode
package scala

/**
 * 语法树节点标签枚举类
 */
object Label extends Enumeration {
  type Label = Value
  val Id, Factor, Term, Expression = Value
}
