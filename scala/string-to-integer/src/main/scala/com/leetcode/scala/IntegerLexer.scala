package com
package leetcode
package scala

import _root_.scala.collection.immutable.{ List }

class IntegerLexer (val lexemeBegin: Int, val context: String) extends Lexer {
  type T = IntegerLiteral

  private val _token = getToken

  override def token: T = _token

  private def addSubtoken = (IntegerIntermedianRepresentations(_, _, _, _, _)).curried

  private def updateIntermedian(token: Token): AnyRef = {
    if (token.lexeme != "") forward += token.lexeme.length
    addSubtoken(token)
  }

  private def getToken: IntegerLiteral = {
    updateIntermedian(new PrefixLexer(getIndex, context).token)
    updateIntermedian(new DigitsLexer(getIndex, context).token)
    updateIntermedian(new InfixLexer(getIndex, context).token)
    updateIntermedian(new DigitsLexer(getIndex, context).token)

    val intermedian = updateIntermedian(new PostfixLexer(getIndex, context).token)
    val lexeme = context.substring(lexemeBegin, forward)

    new IntegerLiteral(lexeme, intermedian.asInstanceOf[IntermedianRepresentations])
  }
}
