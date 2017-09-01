package com
package leetcode
package scala

/**
 * @constructor 数字的词法单元
 * @param lexeme 对应词素
 */
class Num(val lexmem: String) extends Token {
  val tag = Tag.Num
}
