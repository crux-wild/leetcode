package com
package leetcode
package scala

case class LexicalParseFailException(
  message: String = "", cause: Throwable = None.orNull)
extends Exception(message, cause)
