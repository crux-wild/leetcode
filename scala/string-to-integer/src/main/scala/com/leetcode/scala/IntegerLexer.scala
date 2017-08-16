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
    val digits1 = new DigitsLexer(getIndex, context, radix.value).token
    forward = forward + digits1.lexeme.length
    val notation = new InfixLexer(getIndex, context).token
    forward = forward + notation.lexeme.length
    val digits2 = new DigitsLexer(getIndex, context, radix.value).token
    forward = forward + digits2.lexeme.length
    val type1 = new PostfixLexer(getIndex, context).token
    forward = forward + type1.lexeme.length

    val value = calculateValue(radix, digits1, notation, digits2, type1)
    val lexeme = context.substring(lexemeBegin, forward)
    new IntegerLiteral(lexeme, value)
  }

  private def calculateValue(radix: Radix, digits1: Digits, notation: Notation,
    digits2: Digits, long: Type): AnyVal = {
    // 科学计数法只对10进制的时候有效
    val container = if (notation.lexeme == "e" && radix.value == 10) {
      // 不包含有效幂值
      if (digits2.lexeme.length == 0) {
        throw new LexicalParseFailException
      } else {
        // 包含有效幂值
        val power = getDigitsValue(digits2.lexeme, radix.value)
        val base = getDigitsValue(digits1.lexeme, radix.value)
        math.pow(10, power) * base
      }
    } else {
      getDigitsValue(digits1.lexeme, radix.value)
    }
    long.lexeme match {
      case "l" => container.toLong
      case "" => container.toInt
    }
  }

  private def getDigitsValue(digits: String, radix: Byte): Double = {
    var value = if (digits.length == 0) Double.NaN else 0.0
    val reverseDigits = digits.reverse
    for (index <- 0 to (digits.length - 1)) {
      val digit = reverseDigits.apply(index)
      if (radix == 8) {
        if (digit >= '0' && digit <= '7')
          value = value + (digit - '0') * math.pow(radix, index)
        else
          throw new LexicalParseFailException
      }

      if (radix == 10) {
        if (digit >= '0' && digit <= '9')
          value = value + (digit - '0') * math.pow(radix, index)
        else
          throw new LexicalParseFailException
      }

      if (radix == 16) {
        if (digit >= 'a' && digit <= 'f')
          value = value + (digit - 'a' + 10) * math.pow(radix, index)
        else if (digit >= '0' && digit <= '9')
          value = value + (digit - '0') * math.pow(radix, index)
        else
          throw new LexicalParseFailException
      }
    }
    value
  }
}
