package com
package leetcode
package scala

/**
 * @constructor 整数字面量所包含的计数词法单元
 * @param lexeme 对应词素
 */
class Notation(val lexeme: String) extends Token {
  val tag = Tag.Notation
}
