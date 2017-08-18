package com
package leetcode
package scala

trait Lexer[T <: Token] {
  var lexemeBegin: Int
  var forward: Int = -1
  val context: String
  var status = 0

  def token: T

  def currentIndex: Int = lexemeBegin + forward

  def nextChar: Char = {
    forward = forward + 1
    currentChar
  }

  def currentChar: Char = {
    if ((currentIndex < context.length) && (currentIndex >= 0))
      context.apply(currentIndex)
    else
      0.toChar
  }
}
