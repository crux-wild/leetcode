package com
package leetcode
package scala

class InfixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Notation

  private val _token = getToken

  override def token: Notation = _token

  private def getToken: Notation = {
    while (true) {
      var char = '\0'
      try {
        char = nextChar.toLower
      } catch {
        case e: IndexOutOfBoundsException => return
      }
      status match {
        case 0 => if (char == 'e')
                    status = 1 else return new Notation("")
        case 1 => return new Notation("e")
      }
    }
  }
}
