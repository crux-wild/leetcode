package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ LinkedList }

class WholeLexer(val context: String, val lexemeBegin: Int) extends Lexer[Whole] {
  private val _tokenList = new LinkedList[Token]()
  private val _token = getToken

  def token = _token

  private def getToken: Whole = {
    while (true) {
      status match {
        case 0 =>
      }
    }
    new Whole(1)
  }
}
