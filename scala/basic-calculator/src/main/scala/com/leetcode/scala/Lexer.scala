package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }
import _root_.scala.util.matching.{ Regex }

trait Lexer {
  val blankPattern: Regex = raw"[\s]{1,}".r
  var lexemeBegin: Int
  var forward: Int = -1
  val context: String
  var status = 0

  def tokenList: ListBuffer[Token]

  def currentIndex: Int = lexemeBegin + forward

  def nextChar: Char = {
    forward = forward + 1
    var string: String = new String(Array(currentChar))
    var char: Char = currentChar
    while (blankPattern.findFirstIn(string) != None) {
      forward = forward + 1
      string = new String(Array(currentChar))
    }
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
