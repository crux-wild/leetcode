package com
package leetcode
package scala

class PostfixLexer[C](val lexemeBegin: Byte, val context: C) extends Lexer[C] {
  type P = Byte
  type T = Type

  private var _token = null

  override def token: T = _token
}
