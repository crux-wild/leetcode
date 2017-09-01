package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }

class BasicCalculatorLexer(val context: String, var lexemeBegin: Int)
  extends Lexer {

  def tokenList = _scan

  private val integerPattern = raw"[0-9]{1,}".r

  private def isBcdChar(char: Char) = (char >= '0' && char <= '9')

  private def _scan: ListBuffer[Token] = {
    val tokenList = new ListBuffer[Token]()
    while (nextChar != 0) {
      // 匹配整数的情况
      if (isBcdChar(currentChar)) {
        val surplusContext = context.substring(lexemeBegin)
        val lexeme = integerPattern.findFirstIn(surplusContext) match {
          case Some(lexeme) => lexeme
          case None => ""
        }
        tokenList += new Num(lexeme)
      } else {
        // 匹配操作数和其他的情况
        val opVal = currentChar match {
          case '(' => Op.LeftBracket
          case ')' => Op.RightBracket
          case '+' => Op.Plus
          case '-' => Op.Minus
          case '*' => Op.Multiply
          case '%' => Op.Aliquot
          case '/' => Op.Divide
          case _ => throw LexicalAnalysisException(s"Unexpected $currentChar;")
        }
        tokenList += new Op(opVal)
      }
    }
    return tokenList
  }
}
