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

  private def calculateValue(radix: Radix, digits1: Digits, notation: Notation,
    digits2: Digits, type1: Type): AnyVal = {

    val hasE = (notation.lexeme == "e")
    val radixValue = radix.value
    val typeLexeme = type1.lexeme
    val digitsLexeme1 = digits1.lexeme
    val digitsLexeme2 = digits2.lexeme

    var container = 0.0
    var base = 0.0
    var power = 0.0

    base = calculateDigitsValue(digitsLexeme1, radixValue)
    if (hasE == true) {
      if (digitsLexeme2.length == 0)
        new LexicalParseFailException
      else
        power = calculateDigitsValue(digitsLexeme2, radixValue)
        container = container + math.pow(10, power) + base
    } else {
      container = container + base
    }

    typeLexeme match {
      case "l" => container.asInstanceOf[Long]
      case _ => container.asInstanceOf[Int]
    }
  }


  private def calculateDigitsValue(digits: String, radix: Byte): Double = {
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
