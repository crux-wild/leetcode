package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }

trait Lexer {
  val rblank = raw"[\s\S]".r
  var lexemeBegin: Int
  var forward: Int = -1
  val context: String
  var status = 0

  def tokenList: ListBuffer[Token]

  def currentIndex: Int = lexemeBegin + forward

  def nextChar: Char = {
    forward = forward + 1
    while (rblank.findFirstMatchIn(Array(currentChar)) == None)
      forward = forward + 1
    currentChar
  }

  def currentChar: Char = {
    val index = currentIndex
    if ((index < context.length) && (index >= 0))
      context.apply(index)
    else
      0.toChar
  }
}
