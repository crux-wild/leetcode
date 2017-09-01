package com
package leetcode
package scala

import org.scalatest.{ FlatSpec }

import _root_.com.leetcode.scala.BasicCalculatorLexer

class BasicCalculatorLexerSpec extends FlatSpec {
  private val leftBracket = "<op, leftBracket>"
  private val rightBracket = "<op, rightBracket>"
  private val number = "<num, 1>"
  private val plus = "<op, plus>"
  private val minus = "<op, minus>"
  private val multiply = "<op, multiply>"
  private val divide = "<op, division>"
  private val aliquot = "<op, aliquot>"

  behavior of "basic calculator lexer"

  it should "return the specified content;" in {
    val context = "()+-*/%1"
    val basicCalculatorLexer = new BasicCalculatorLexer(context, 0)

    //val serialization = s"$leftBracket $rightBracket $number $plus $minus $multiply $divide $aliquot"
    val serialization = ""
    assert(basicCalculatorLexer.toString == serialization)
  }
}
