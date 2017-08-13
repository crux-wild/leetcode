package com
package leetcode
package scala

class PrefixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Radix

  private val _token = getToken
  private var _status = 0

  override def token: Radix = _token

  private def getToken: Radix = {
    while (true) {
      _status match {
        case 0 => if (nextChar.toLower == '0')
                    _status = 1 else LexicalParseFailException
        case 1 => if(nextChar.toLower == 'x')
                    _status = 2 else return new Radix("0", 8)
        case 2 => return new Radix("0x", 16)
      }
    }
  }
}
