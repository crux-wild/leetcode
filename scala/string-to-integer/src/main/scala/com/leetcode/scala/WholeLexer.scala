package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ ListBuffer }
import _root_.scala.{ math }

/**
 * @constructor 整数字面量词法分析器
 * @param context 词法解析的上下文
 * @param lexemeBegin 词法单元的开始指针
 */
class WholeLexer(val context: String, var lexemeBegin: Int) extends Lexer[Whole] {
  private val _intermediate = new Intermediate()
  private val _token = getToken

  def token = _token

  private def getToken: Whole = {
    val value = getValue
    new Whole(value)
  }

  private def getValue: AnyVal = {
    updateIntermediate
    caculateIntermediateValue
  }

  private def updateIntermediate: Unit = {
    var digitsCount = 0
    getTokenList.foreach { token =>
      token match {
        case radix: Radix => _intermediate.prefix = radix
        case digits: Digits => {
          if (digitsCount == 0) {
            _intermediate.digits1 = digits
            digitsCount = digitsCount + 1
          } else if (digitsCount == 1) {
            _intermediate.digits2 = digits
          }
        }
        case notation: Notation => _intermediate.infix = notation
        case long: Long => _intermediate.suffix = long
      }
    }
  }

  private def caculateIntermediateValue: AnyVal = {
    val radix = _intermediate.prefix.value
    val digits1 = _intermediate.digits1.lexeme
    val notation = _intermediate.infix.lexeme
    val digits2 = _intermediate.digits2.lexeme
    val long = _intermediate.suffix.lexeme

    val base = getDigitsValue(digits1, radix)
    val value = radix match {
      case 8 => base
      case 16 => base
      case 10 => notation match {
        case "e" => {
          val power = getDigitsValue(digits2, radix)
          base * math.pow(10, power)
        }
        case _ => base
      }
    }
    long match {
      case "l" => if (!value.isNaN) value.toLong else value
      case "" => if (!value.isNaN) value.toInt else value
    }
  }

  private def isHexDigit(char: Char) =
    ((char <= 'f' && char >= 'a') || (char <='9' && char >= '0'))
  private def isBcdDigit(char: Char) = (char <= '9' && char >= '0')
  private def isOctDigit(char: Char) = (char <= '7' && char >= '0')

  private def getDigitsValue(digits: String, radix: Byte): Double = {
    val rDigits = digits.reverse
    var value = Double.NaN
    for (index <- 0 to (rDigits.length - 1)) {
      val digit = rDigits.apply(index).toLower
      val base = radix match {
        case 8 if (isOctDigit(digit)) => bcdChar2Int(digit)
        case 10 if (isBcdDigit(digit)) => bcdChar2Int(digit)
        case 16 if (isHexDigit(digit)) => hexChar2Int(digit)
        case _ => Double.NaN
      }
      if ((!base.isNaN) && (value.isNaN)) value = 0.0
      value = value + base.toDouble * math.pow(radix, index)
    }
    value
  }

  private def bcdChar2Int(char: Char): Int = char - '0'
  private def hexChar2Int(char: Char): Int = {
    if (char <= 'f' && char >= 'a')
      char - 'a' + 10
    else
      char - '0'
  }

  private def moveLexemeBegin(offset: Int = 0): Unit = {
    lexemeBegin = lexemeBegin + forward + offset
    forward = 0
  }
  private def getLexeme(offset: Int = 0): String = {
    context.substring(lexemeBegin, lexemeBegin + forward + offset)
  }

  private def getTokenList: ListBuffer[Token] = {
    val tokenList = new ListBuffer[Token]()
    while (true) {
      status match {
        case 0 => if (nextChar.toLower == '0') status = 1 else status = 6
        case 1 =>  {
          if (nextChar.toLower == 'x') {
            status = 2
            tokenList += new Radix(16)
            moveLexemeBegin(1)
          } else {
            status = 5
            tokenList += new Radix(8)
            moveLexemeBegin(-1)
          }
        }
        case 2 => if (isHexDigit(nextChar.toLower)) status = 3 else status = 11
        case 3 => {
          if (isHexDigit(nextChar.toLower)) {
            status = 3
          } else {
            status = 11
            tokenList += new Digits(getLexeme(0))
            moveLexemeBegin(-1)
          }
        }
        case 4 => if (isOctDigit(nextChar.toLower)) status = 6 else status = 11
        case 5 => {
          if (isOctDigit(nextChar.toLower)) {
            status = 5
          } else {
            status = 11
            tokenList += new Digits(getLexeme(0))
            moveLexemeBegin(-1)
          }
        }
        case 6 => if (isBcdDigit(currentChar.toLower)) status = 7 else status = 11
        case 7 => {
          if (isBcdDigit(nextChar.toLower)) {
            status = 7
          } else {
            status = 8
            tokenList += new Digits(getLexeme(0))
            moveLexemeBegin(-1)
          }
        }
        case 8 => {
          if (nextChar.toLower == 'e') {
            tokenList += new Notation("e")
            moveLexemeBegin(1)
            status = 9
          } else {
            status = 11
          }
        }
        case 9 => {
          if (isBcdDigit(currentChar.toLower)) {
            status = 10
          } else {
            tokenList += new Digits(getLexeme(0))
            moveLexemeBegin(-1)
            status = 11
          }
        }
        case 10 => {
          if (isBcdDigit(nextChar.toLower)) {
            status = 10
          } else {
            status = 11
            tokenList += new Digits(getLexeme(0))
            moveLexemeBegin(-1)
          }
        }

        case 11 => {
          if (currentChar.toLower == 'l') {
            tokenList += new Long("l")
            moveLexemeBegin(0)
            return tokenList
          } else {
            return tokenList
          }
        }
      }
    }
    tokenList
  }
}
