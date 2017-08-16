package com
package leetcode
package scala

class StringToInteger(val literal: String) {
  private var _value: AnyVal = getValue

  def value = _value

  private def getValue(): AnyVal = {
    new IntegerLexer(0, literal).token.value
  }
}
