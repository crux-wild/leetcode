package com
package leetcode
package scala

import _root_.scala.util.matching.{ Regex }

object Tokenizer {
  def tokenization(InputCharacters: CharSequence): String = {
    val rblank: Regex = raw"/s".r
    rblank.replaceAllIn(InputCharacters, "")
  }
}
