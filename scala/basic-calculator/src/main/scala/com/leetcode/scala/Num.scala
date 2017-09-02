package com
package leetcode
package scala

/**
 * @constructor 数字的词法单元
 * @param lexeme 对应词素
 */
class Num(val lexeme: String) extends Token {
  val tag = Tag.Num

  def value = _value

  override def toString(): String = {
    val value = _value
    s"<num, $value>"
  }

  private val _value = getValue

  private def getValue: AnyVal = {
    // 根据题目暂时只解析整数
    lexeme.toInt
  }
}
