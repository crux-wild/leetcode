package com
package leetcode
package scala

class PostfixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Type

  private val _token = getToken
  private var _status = 0

  override def token: T = _token

  private def getToken: Type = {
    while (true) {
      var char = nextChar.toLower
      status match {
        case 0 => if (char.toLower == 'l')
                    status = 1 else LexicalParseFailException
        case 1 => return new Type("l")
      }
    }
  }
}
