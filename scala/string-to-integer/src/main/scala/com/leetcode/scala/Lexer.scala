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
    forward += 1
    val index = getIndex
    if (index < context.length)
      context.apply(index)
    else
      throw new IndexOutOfBoundsException
  }

  def token: T
}
