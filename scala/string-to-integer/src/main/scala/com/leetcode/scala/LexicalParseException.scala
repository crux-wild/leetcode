package com
package leetcode
package scala

case class LexicalParseException(
  private val message: String = "",
  private val cause: Throwable = None.orNull)
extends Exception(message, cause)

