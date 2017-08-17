package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }

class WholeLexer(val context: String, val lexemeBegin: Int) extends Lexer[Whole] {
  private val _tokenList = new ListBuffer[Token]()
  private val _token = getToken

  def token = _token

  private def isHexDigit(char: Char) =
    ((char <= 'f' && char >= 'a') || (char <='9' && char >= '0'))
  private def isBcdDigit(char: Char) = (char <= '9' && char >= '0')
  private def isOctDigit(char: Char) = (char <= '7' && char >= '0')

  private def getToken: Whole = {
    val value = getValue
    new Whole(value)
  }

  private def getValue: AnyVal = {
    while (true) {
      status match {
        case 0 => if (nextChar.toLower == '0') status = 1 else status =
        case 1 => if (nextChar.toLower == 'x') status = 2 else _tokenList += new Radix(8)
        case 2 => if (isHexChar(nextChar.toLower)) status = 3 else _tokenList += new Radix(16)
        case 3 => if (isHexChar(nextChar.toLower)) status = 3 else status = 4
        case 4 => if ()
      }
    }
  }
}
