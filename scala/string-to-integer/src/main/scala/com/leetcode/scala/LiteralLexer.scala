package com
package leetcode
package scala

class LiteralLexer(val lexemeBegin: Byte) extends Lexer {
  type P = Byte
  type T = Type

  private var _token = null

  override def token: T = _token
}
