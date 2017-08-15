package com
package leetcode
package scala

class IntegerLexer (val lexemeBegin: Int, val context: String) extends Lexer {
  type T = IntegerLiteral

  private val _token = getToken

  override def token: T = _token

  private def addSubtoken = (IntegerIntermedianRepresentations(_, _, _, _, _)).curried
  private def updateIntermedian(token: Token): AnyRef = {
    println(addSubtoken)
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
    val value = calculateIntegerValue(intermedian.asInstanceOf[IntegerIntermedianRepresentations])

    new IntegerLiteral(lexeme, value)
  }

  private def calculateIntegerValue(
    intermedian: IntegerIntermedianRepresentations): AnyVal = {

    val hasE = (intermedian.notation.lexeme == "e")
    val radix = intermedian.radix.asInstanceOf[Radix].value
    val type1 = intermedian.type1.lexeme
    val digits1 = intermedian.digits1.lexeme
    val digits2 = intermedian.digits2.lexeme

    val base: Double = calculateDigitsValue(digits1, radix)
    var power: Double = 0
    var container: Double = 0

    if (hasE == false)
      container += base
    else
      if (digits2.length == 0)
        new LexicalParseFailException
      else
        power = calculateDigitsValue(digits2, radix)
        container += (math.pow(10, power) + base)

    type1 match {
      case "l" => container.asInstanceOf[Long]
      case _ => container.asInstanceOf[Int]
    }
  }

  private def calculateDigitsValue(digits: String, radix: Byte): Double = {
    var value = 0
    digits.foreach { digit =>
      if (radix == 10 && (digit >= '0' && digit <= '9'))
        value += digit - '0'
      else
        throw new LexicalParseFailException

      if (radix == 16 && (digit >= 'a' && digit <= 'f'))
        value += digit - 'a' + 10
      else
        throw new LexicalParseFailException
    }
    value
  }
}
