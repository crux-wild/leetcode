package com
package leetcode
package scala

class Literal(val lexeme: String) extends Token {
  val tag: Tag.Value = Tag.LITERAL
}
