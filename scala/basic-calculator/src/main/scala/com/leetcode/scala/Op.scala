package com
package leetcode
package scala

/**
 * @constructor 操作数的词法单元
 * @param lexeme 对应词素
 */
class Op(val opVal: Op.Value) extends Token {
  val tag = Tag.Op

  override def toString(): String = {
    val op = opVal match {
      case Op.Plus => "plus"
      case Op.Minus => "minus"
      case Op.Multiply => "multiply"
      case Op.Division => "division"
      case Op.Aliquot => "aliquot"
      case Op.LeftBracket => "leftBracket"
      case Op.RightBracket => "rightBracket"
    }
    s"<op, $op>"
  }
}

object Op extends Enumeration {
  type Op = Value
  val Plus, Minus, Multiply, Division, Aliquot, LeftBracket, RightBracket = Value
}
