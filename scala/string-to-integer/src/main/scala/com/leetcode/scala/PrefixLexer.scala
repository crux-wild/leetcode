package com
package leetcode
package scala

class PrefixLexer(val lexemeBegin: Byte) extends Lexer {
  type P = Byte
  type T = Radix

  private var _token = null

  override def token: T = _token
}
