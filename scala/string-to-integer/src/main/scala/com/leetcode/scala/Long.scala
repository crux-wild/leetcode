package com
package leetcode
package scala

/**
 * @constructor 整数字面量内存长度的词法单元
 * @param lexeme 对应词素
 */
class Long(val lexeme: String) extends Token {
  val tag = Tag.Long
}
