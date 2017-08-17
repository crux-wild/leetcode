package com
package leetcode
package scala

class IntegerLexer (val lexemeBegin: Int, val context: String) extends Lexer {
  type T = IntegerLiteral

  private val _token = getToken

  override def token: T = _token

  private def getToken: IntegerLiteral = {
    val value = getValue()
    new IntegerLiteral(context.substring(lexemeBegin, lexemeBegin + forward), value)
  }

  private def getValue(): AnyVal = {
    while (true) {
      println("status: " +  status)
      println("char: " + currentChar)
      status match {
        case 0 => if (nextChar.toLower == '0') status = 1 else status = 14
        case 1 => if (nextChar.toLower == 'x') status = 2 else status = 8
        case 2 => if (isHexChar(nextChar.toLower)) status = 3 else status = 7
        case 3 => if (isHexChar(nextChar.toLower)) status = 3 else status = 4
        case 4 => if (nextChar.toLower == 'l') status = 5 else status = 6
        case 5 => return calculateValue(getHexRadix, getDigits(), null, null, getLongType)
        case 6 => return calculateValue(getHexRadix, getDigits(), null, null, null)
        case 7 => throw new LexicalParseFailException

        case 8 => if (isOctChar(nextChar.toLower)) status = 9 else status = 13
        case 9 => if (isOctChar(nextChar.toLower)) status = 9 else status = 10
        case 10 => if (nextChar.toLower == 'l') status = 11 else status = 12
        case 11 => return calculateValue(getOctRadix, getDigits(), null, null, getLongType)
        case 12 => return calculateValue(getOctRadix, getDigits(), null, null, null)
        case 13 => throw new LexicalParseFailException

        case 14 => if (isBcdChar(currentChar.toLower)) status = 15 else throw new LexicalParseFailException
        case 15 => if (isBcdChar(nextChar.toLower)) status = 15 else status = 16
        case 16 => if (currentChar.toLower == 'e') status = 17 else status = 19
        case 17 => if (isBcdChar(nextChar.toLower)) status = 18 else status = 22
        case 18 => if (isBcdChar(nextChar.toLower)) status = 18 else status = 19
        case 19 => if (currentChar.toLower == 'l') status = 20 else status = 21
        case 20 => return calculateValue(getBcdRadix, getDigits(), null, null, getLongType)
        case 21 => return calculateValue(getBcdRadix, getDigits(), null, null, null)
        case 22 => throw new LexicalParseFailException
      }
    }
  }

  private def isOctChar(char: Char): Boolean = (char <= '7' && char >= '0')
  private def isBcdChar(char: Char): Boolean = (char <= '9' && char >= '0')
  private def isHexChar(char: Char): Boolean =
    ((char <= '9' && char >= '0') || (char <= 'f' && char >= 'a'))
  private def getHexRadix: Radix = new Radix("0x", 16)
  private def getBcdRadix: Radix = new Radix("", 10)
  private def getOctRadix: Radix = new Radix("0", 8)
  private def getLongType: Type = new Type("l")
  private def getDigits(start: Int = lexemeBegin, offset: Int = forward): Digits
    = new Digits(context.substring(start, start + offset - 1))

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
