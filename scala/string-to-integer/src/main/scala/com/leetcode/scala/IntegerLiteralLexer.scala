package com
package leetcode
package scala

import _root_.scala.collection.immutable.{ List }

class IntegerLiteralLexer (val lexemeBegin: Int, val context: String)
  extends Lexer {

  type T = IntermedianRepresentations

  private val _token = getToken
  private var _intermedianRepresentations: IntermedianRepresentations = null

  override def token: T = _token

  private def getIndex: Int = {
    lexemeBegin + forward
  }

  private def parseSubToken(lexer: Lexer): IntermedianRepresentations = {
    val token: Token = lexer.token

    if (_intermedianRepresentations == null)
      _intermedianRepresentations = IntegerIntermedianRepresentations(token, _ : Token)
    else
     _intermedianRepresentations = _intermedianRepresentations(token, _ : Token)
    // 没有匹配到非空的词法单元
    if (token.lexeme != "") forward = forward + token.lexeme.length

    _intermedianRepresentations
  }

  private def getToken: T = {
    parseSubToken(new PrefixLexer(getIndex, context))
    parseSubToken(new DigitsLexer(getIndex, context))
    parseSubToken(new InfixLexer(getIndex, context))
    parseSubToken(new DigitsLexer(getIndex, context))
    parseSubToken(new PostfixLexer(getIndex, context))

    _intermedianRepresentations
  }
}
