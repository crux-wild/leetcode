package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }

class WholeLexer(val context: String, var lexemeBegin: Int) extends Lexer[Whole] {
  private val _tokenList = new ListBuffer[Token]()
  private val _intermediate = new Intermediate()
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
    updateIntermediate
  }

  private def updateIntermediate: Unit = {
    var digitsCount = 0
    fillTokenList
    _tokenList.foreach { token =>
      token match {
        case radix: Radix => _intermediate.prefix = radix
        case digits: Digits => {
          if (digitsCount == 0)
            _intermediate.digits1 = digits
          else
            _intermediate.digits2 = digits
        }
        case notation: Notation => _intermediate.infix = notation
        case long: Long => _intermediate.suffix = long
      }
    }
  }

  private def moveLexemeBegin(recall: Int = 0): Unit = {
    lexemeBegin = lexemeBegin + forward - recall
  }

  private def getLexeme(recall: Int = 0): String
    = context.substring(lexemeBegin, lexemeBegin + forward + recall)

  private def fillTokenList: Unit = {
    while (true) {
      status match {
        case 0 => if (nextChar.toLower == '0') status = 1 else status = 1000
        case 1 =>  {
          if (nextChar.toLower == 'x') {
            status = 2
            _tokenList += new Radix(16)
            moveLexemeBegin(0)
          } else {
            _tokenList += new Radix(8)
            moveLexemeBegin(-1)
          }
        }
        case 2 => if (isHexDigit(nextChar.toLower)) status = 3 else status = 1000
        case 3 => {
          if (isHexDigit(nextChar.toLower))
            status = 3
          else
            status = 4
            _tokenList += new Digits(getLexeme(0))
            moveLexemeBegin(-1)
        }
        case 4 => {
          if (nextChar.toLower == 'l') {
            status = 4
            _tokenList += new Long("l")
            moveLexemeBegin(0)
          } else {
            status = 1000
          }
        }
      }
    }
  }
}
