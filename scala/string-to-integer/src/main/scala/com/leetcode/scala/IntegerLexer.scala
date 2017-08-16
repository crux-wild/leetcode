package com
package leetcode
package scala

class IntegerLexer (val lexemeBegin: Int, val context: String) extends Lexer {
  type T = IntegerLiteral

  private val _token = getToken

  override def token: T = _token

  private def getToken: IntegerLiteral = {
    val radix =  new PrefixLexer(getIndex, context).token
    forward = forward + radix.lexeme.length
    val digits1 = new DigitsLexer(getIndex, context).token
    forward = forward + digits1.lexeme.length
    val notation = new InfixLexer(getIndex, context).token
    forward = forward + notation.lexeme.length
    val digits2 = new DigitsLexer(getIndex, context).token
    forward = forward + digits2.lexeme.length
    val type1 = new PostfixLexer(getIndex, context).token
    forward = forward + type1.lexeme.length

    val value = calculateValue(radix, digits1, notation, digits2, type1)
    val lexeme = context.substring(lexemeBegin, forward)
    new IntegerLiteral(lexeme, value)
  }

  private def calculateValue(token1: Radix, token2: Digits, token3: Notation,
    token4: Digits, token5: Type): AnyVal = {

    val notation = token3.lexeme
    val digits1 = token2.lexeme
    val digits2 = token4.lexeme
    val radix = token1.value
    val long = token5.lexeme
    var container = 0.0
    if (notation != "e") {
      val base = getDigitsValue(digits1, radix)
      container = container + base
    } else {
      // 不包含有效幂值
      if (digits2.length == 0) {
        new LexicalParseFailException
      } else {
        // 包含有效幂值
        val power = getDigitsValue(digits2, radix)
        val base = getDigitsValue(digits1, radix)
        container = container + math.pow(10, power) + base
      }
    }
    long match {
      case "l" => container.toLong
      case _ => container.toInt
    }
  }

  private def getDigitsValue(digits: String, radix: Byte): Double = {
    val reverseDigits = digits.reverse
    var value = 0.0
    for (index <- 0 to (digits.length - 1)) {
      val digit = reverseDigits.apply(index)
      if (radix == 8)
        if (digit >= '0' && digit <= '7')
          value = value + (digit - '0') * math.pow(10, index)
        else
          throw new LexicalParseFailException

      if (radix == 10)
        if (digit >= '0' && digit <= '9')
          value = value + (digit - '0') * math.pow(10, index)
        else
          throw new LexicalParseFailException

      if (radix == 16)
        if (digit >= 'a' && digit <= 'f')
          value = value + (digit - '0') * math.pow(10, index)
        else
          throw new LexicalParseFailException
    }
    value
  }
}
