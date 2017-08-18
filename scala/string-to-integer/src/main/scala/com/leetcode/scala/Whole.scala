package com
package leetcode
package scala

/**
 * @constructor 整数字面量的词法单元
 * @param value 对应整数值
 */
class Whole(val value: AnyVal) extends Token {
  val tag = Tag.Whole
}
