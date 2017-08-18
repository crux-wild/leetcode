package com
package leetcode
package scala

/**
 * @constructor 整数字面量所包含数字组的词法单元
 * @param lexeme 对应词素
 */
class Digits(val lexeme: String) extends Token {
  val tag = Tag.Digits
}
