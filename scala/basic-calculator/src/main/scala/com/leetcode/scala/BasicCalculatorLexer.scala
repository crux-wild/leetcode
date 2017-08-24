package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }

class BasicCalculatorLexer(val context: String, var lexemeBegin: Int)
  extends Lexer {

  def tokenList = _scan

  private def _scan: ListBuffer[Token] = {
    val tokenList = new ListBuffer[Token]()
    while (true) {
    }
    tokenList
  }
}
