package com
package leetcode
package scala

/**
 * @constructor 数字的词法单元
 * @param lexeme 对应词素
 */
class Number(val value: Int) extends Token {
  val tag = Tag.Number
}
