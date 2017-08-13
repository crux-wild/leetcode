package com
package leetcode
package scala

class PrefixLexer[C](val lexemeBegin: Byte, val context: C) extends Lexer[C] {
  type P = Byte
  type T = Radix

  private var _token = null

  override def token: T = _token
}
