package com
package leetcode
package scala

/**
 * @constructor 操作数的词法单元
 * @param lexeme 对应词素
 */
class Op(val lexeme: Op.Value) extends Token {
  val tag = Tag.Op
}

object Op extends Enumeration {
  type Op = Value
  val Plus, Minus, Multiply, Divide, Aliquot, LeftBracket, RightBracket = Value
}
