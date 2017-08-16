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
      status match {
        case 0 => if (nextChar.toLower == 'l') status = 2 else status = 1
        case 1 => return new Type("")
        case 2 => return new Type("l")
      }
    }
  }
}
