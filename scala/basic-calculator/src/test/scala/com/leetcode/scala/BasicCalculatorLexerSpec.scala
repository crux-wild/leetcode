package com
package leetcode
package scala

import _root_.org.scalatest.{ FlatSpec }
import _root_.com.leetcode.scala.{ BasicCalculatorLexer }

class BasicCalculatorLexerSpec extends FlatSpec {
  private val lBracket = "<op, leftBracket>"
  private val rBracket = "<op, rightBracket>"
  private val plus = "<op, plus>"
  private val minus = "<op, minus>"
  private val multiply = "<op, multiply>"
  private val divide = "<op, division>"
  private val aliquot = "<op, aliquot>"
  private val num = "<num, 1>"

  behavior of "basic calculator lexer"

  it should "return the specified content;" in {
    val context = "()+-*/%1"
    val basicCalculatorLexer = new BasicCalculatorLexer(context, 0)

    val serialization = s"$lBracket $rBracket $plus $minus $multiply $divide $aliquot $num"
    assert(basicCalculatorLexer.toString == serialization)
  }
}
