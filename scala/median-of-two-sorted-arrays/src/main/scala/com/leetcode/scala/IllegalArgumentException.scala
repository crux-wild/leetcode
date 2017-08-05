package com.leetcode.scala

case class IllegalArgumentException(
  val message: String = "One or more argument is illegal",
  val cause: Throwable = None.orNull)
extends Exception(message, cause)
