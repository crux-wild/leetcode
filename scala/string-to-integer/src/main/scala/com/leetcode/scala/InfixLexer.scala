package com
package leetcode
package scala

class InfixLexer(val lexemeBegin: Byte) extends Lexer {
  type P = Byte
  type T = Notation

  private var _token = null

  override def token: T = _token
}
