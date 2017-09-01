package com
package leetcode
package scala

case class LexicalAnalysisException(
  val message: String = "",
  val cause: Throwable = None.orNull)
extends Exception(message, cause)
