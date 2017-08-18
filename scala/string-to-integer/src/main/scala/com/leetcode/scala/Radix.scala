package com
package leetcode
package scala

/**
 * @constructor 整数字面量所包含的数制 词法单元
 * @param value 对应数制值
 */
class Radix(val value: Byte) extends Token {
  val tag = Tag.Radix
}
