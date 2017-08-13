package com
package leetcode
package scala

trait Lexer {
  type T

  val context: String
  val lexemeBegin: Int
  var forward: Int = 0

  def nextChar: Char = {
    var index = lexemeBegin + forward

    index = index + 1

    if (index < context.length)
      context.apply(index)
    else
      // @TODO
      '0'
  }

  def token: T
}
