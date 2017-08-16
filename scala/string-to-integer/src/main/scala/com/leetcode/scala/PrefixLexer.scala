package com
package leetcode
package scala

class PrefixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Radix

  private val _token = getToken

  override def token: Radix = _token

  private def getToken: Radix = {
    while (true) {
      status match {
        case 0 => if (nextChar.toLower == '0')
                    status = 1 else return new Radix("", 10)
        case 1 => if(nextChar.toLower == 'x')
                    status = 2 else return new Radix("0", 8)
        case 2 => return new Radix("0x", 16)
      }
    }
  }
}
