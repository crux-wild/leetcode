package com
package leetcode
package scala

class IntegerLiteralLexer[C]
  (val lexemeBegin: Byte, val context: C) extends Lexer[C] {

  type P = Byte
  type T = IntermedianRepresentations

  private var _token = null

  override def token: T = _token
}
