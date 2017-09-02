package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }
import _root_.scala.util.matching.{ Regex }

class BasicCalculatorLexer(val context: String, var lexemeBegin: Int)
  extends Lexer {

  def tokenList = _tokenList

  override def toString(): String = {
    val serializeList = _tokenList.map((token) => {
      token.toString()
    })
    serializeList.mkString(" ")
  }

  private val _tokenList = scan()

  private def isBcdChar(char: Char) = char >= '0' && char <= '9'
  private def scan(): ListBuffer[Token] = {
    val tokenList = new ListBuffer[Token]()
    val integerPattern = "[0-9]{1,}".r
    while (nextChar != 0) {
      // 匹配整数的情况
      if (isBcdChar(currentChar)) {
        val surplusContext = context.substring(lexemeBegin)
        val lexeme = integerPattern findFirstIn surplusContext match {
          case Some(string) => string
          case None => ""
        }
        tokenList += new Num(lexeme)
        forward = forward + lexeme.length - 1
      } else {
        // 匹配操作数和其他的情况
        val opVal = currentChar match {
          case '(' => Op.LeftBracket
          case ')' => Op.RightBracket
          case '+' => Op.Plus
          case '-' => Op.Minus
          case '*' => Op.Multiply
          case '%' => Op.Aliquot
          case '/' => Op.Division
          case _ => throw LexicalAnalysisException(s"Unexpected $currentChar;")
        }
        tokenList += new Op(opVal)
      }
    }
    return tokenList
  }
}
