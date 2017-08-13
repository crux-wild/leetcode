package com
package leetcode
package scala

import _root_.scala.collection.immutable.{ List }

class IntegerLiteralLexer (val lexemeBegin: Int, val context: String)
  extends Lexer {

  type T = IntermedianRepresentations

  private val _token = getToken

  override def token: T = _token

  private def getIndex: Int = {
    lexemeBegin + forward
  }

  private def getToken: T = {
    val subLexerList = List(PrefixLexer, DigitsLexer, InfixLexer, DigitsLexer,
      PostfixLexer)

    var intermedianRepresentations: AnyRef = Null

    subLexerList.foreach{ Lexer =>
      val index = getIndex()
      val token = new Lexer(index, context)

      intermedianRepresentations = IntermedianRepresentations(token)

      // 没有匹配到非空的词法单元
      if (token.lexeme != "")
        forward = forward + lexeme.length
    }
    intermedianRepresentations
  }
}
