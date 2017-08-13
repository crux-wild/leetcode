package com
package leetcode
package scala

class PostfixLexer(val lexemeBegin: Byte) extends Lexer {
  type P = Byte
  type T = Type

  private var _token = null

  override def token: T = _token
}
