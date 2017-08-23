package com
package leetcode
package scala

/**
 * @constructor 操作数的词法单元
 * @param lexeme 对应词素
 */
class Operate(val lexeme: String) extends Token {
  val tag = Tag.Operate
}
