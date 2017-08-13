package com
package leetcode
package scala

class PostfixLexer(val lexemeBegin: Int, val context: String) extends Lexer {
  type T = Type

  private var _token = null

  override def token: T = _token
}
