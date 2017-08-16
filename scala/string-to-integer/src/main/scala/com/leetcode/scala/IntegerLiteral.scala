package com
package leetcode
package scala

class IntegerLiteral(val lexeme: String, val value: AnyVal) extends Token {
  val tag: Tag.Value = Tag.Literal
}
