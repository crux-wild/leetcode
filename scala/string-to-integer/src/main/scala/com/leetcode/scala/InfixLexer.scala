package com
package leetcode
package scala

class InfixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Notation

  private var _token = null

  override def token: T = _token
}
