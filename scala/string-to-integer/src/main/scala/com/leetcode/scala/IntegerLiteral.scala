package com
package leetcode
package scala

class IntegerLiteral(val lexeme: String,
  val intermedianRepresentations: IntermedianRepresentations) extends Token {

  val tag: Tag.Value = Tag.Literal
}
