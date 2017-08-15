package com
package leetcode
package scala

trait Lexer {
  type T <: Token

  val context: String
  val lexemeBegin: Int
  var forward: Int = 0
  var status = 0

  def getIndex: Int = lexemeBegin + forward

  def nextChar: Char = {
    val index = getIndex
    var char = 0.toChar
    if (index < context.length)
      char = context.apply(index)
      forward = forward + 1
    char
  }

  def token: T
}
