package com
package leetcode
package scala

trait Lexer {
  type T <: Token

  val context: String
  val lexemeBegin: Int
  var forward: Int = -1
  var status = 0

  def getIndex: Int = lexemeBegin + forward

  def nextChar: Char = {
    forward = forward + 1
    currentChar
  }

  def currentChar: Char = {
    val index = getIndex
    var char = 0.toChar
    if ((index < context.length) && (index >= 0))
      char = context.apply(index)
    char
  }

  def token: T
}
