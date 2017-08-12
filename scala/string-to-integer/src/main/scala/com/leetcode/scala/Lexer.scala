package com
package leetcode
package scala

trait Lexer {
  type P
  type S
  type T

  val forward: P
  val lexemeBegin: P
  val status: S

  def token: T
}
