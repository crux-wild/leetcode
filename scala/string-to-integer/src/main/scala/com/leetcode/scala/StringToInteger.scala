package com
package leetcode
package scala

class StringToInteger(string: String) {
  private val _value = getValue

  def value = _value

  private def getValue: AnyVal = {
    new WholeLexer(string, 0).token.value
  }
}
