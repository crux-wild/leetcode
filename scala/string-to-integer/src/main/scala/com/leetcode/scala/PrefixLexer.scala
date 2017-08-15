package com
package leetcode
package scala

class PrefixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Radix

  private val _token = getToken

  override def token: Radix = _token

  private def getToken: Radix = {
    while (true) {
      var char = '\0'
      try {
        char = nextChar.toLower
      } catch {
        case e: IndexOutOfBoundsException => return
      }
      status match {
        case 0 => if (char.toLower == '0')
                    status = 1 else LexicalParseFailException
        case 1 => if(char.toLower == 'x')
                    status = 2 else return new Radix("0", 8)
        case 2 => return new Radix("0x", 16)
      }
    }
  }
}
