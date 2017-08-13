package com
package leetcode
package scala

class IntegerLiteralLexer (val lexemeBegin: Int, val context: String)
  extends Lexer {

  type T = IntermedianRepresentations

  private var _token = null

  override def token: T = _token
}
