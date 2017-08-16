package com
package leetcode
package scala

class DigitsLexer(val lexemeBegin: Int, val context: String,
  val radix: Byte = 16) extends Lexer {

  type T = Digits

  private val _token = getToken

  override def token: Digits = _token

  private def isMatch(char: Char): Boolean = {
    radix match {
      case 8 => (char <= '7' && char >= '0')
      case 10 => (char <= '9' && char >= '0')
      case 16 => ((char <= '9' && char >= '0') || (char <= 'f' && char >= 'a'))
    }
  }

  private def getToken: Digits = {
    while (true) {
      status match {
        case 0 => if (isMatch(nextChar.toLower))
                    status = 1 else return new Digits("")
        case 1 => if (isMatch(nextChar.toLower))
                    status = 1 else status = 2
        case 2 => return new Digits(
                          context.substring(lexemeBegin,
                                            lexemeBegin + forward - 1))
      }
    }
  }
}
