package com
package leetcode
package scala

trait Token {
  val tag: Tag.Value

  override def toString(): String = {
    val hash = hashCode()
    s"<Token, $hash>"
  }
}
