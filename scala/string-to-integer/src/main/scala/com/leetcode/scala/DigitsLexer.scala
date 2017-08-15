package com
package leetcode
package scala

class DigitsLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Digits

  private val _token = getToken

  override def token: Digits = _token

  private def isMatch(char: Char): Boolean = {
    if ((char <= '9' && char >= '0') || (char <= 'f' && char >= 'a'))
      true
    else
      false
  }

  private def getToken: Digits = {
    while (true) {
      var char = '\0'
      try {
        char = nextChar.toLower
      } catch {
        case e: IndexOutOfBoundsException => return
      }
      status match {
        case 0 => if (isMatch(char))
                    status = 1 else throw new LexicalParseFailException
        case 1 => if (isMatch(char))
                    status = 1 else status = 2
        case 2 => return new Digits(context.substring(lexemeBegin, forward - 1))
      }
    }
  }
}
