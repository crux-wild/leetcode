package com
package leetcode
package scala

trait Lexer[T <: Token] {
  var lexemeBegin: Int
  var forward: Int = -1
  val context: String
  var status = 0

  def token: T

  def nextChar: Char = {
    forward = forward + 1
    currentChar
  }

  def currentChar: Char = {
    val index = lexemeBegin + forward
    if ((index < context.length) && (index >= 0))
      context.apply(index)
    else
      0.toChar
  }
}
