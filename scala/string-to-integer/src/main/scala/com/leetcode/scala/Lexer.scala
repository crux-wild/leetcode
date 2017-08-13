package com
package leetcode
package scala

trait Lexer {
  type P
  type T

  val lexemeBegin: P

  def token: T
}
