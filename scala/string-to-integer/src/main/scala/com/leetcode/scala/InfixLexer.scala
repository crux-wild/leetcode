package com
package leetcode
package scala

class InfixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Notation

  private val _token = getToken

  override def token: Notation = _token

  private def getToken: Notation = {
    while (true) {
      status match {
        case 0 => if (nextChar.toLower == 'e')
                    status = 1 else throw new LexicalParseFailException
        case 1 => return new Notation("e")
      }
    }
  }
}
