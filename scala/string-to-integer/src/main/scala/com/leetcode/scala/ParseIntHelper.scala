package com
package leetcode
package scala

import _root_.scala.collection.mutable.{ StringBuilder }

implicit class ParseIntHelper(val sc: StringContext) extends AnyVal {
  def int(args: Any*): AnyVal = {
    val strings = sc.parts.iterator
    var buf = new StringBuffer(strings.next)
    while (string.hasNext) {
      buf append strings.next
    }
  }

  def parseInt(builder: StringBuffer): AnyVal = {
    // @TODO
  }
}
