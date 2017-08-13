package com
package leetcode
package scala

trait Lexer[C] {
  type P
  type T

  val context: C
  val lexemeBegin: P

  def token: T
}
