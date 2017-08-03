package com.leetcode.scala

import scala.collection.mutable.MutableList

import com.leetcode.scala.Mixtrue
import com.leetcode.scala.Area
import com.leetcode.scala.ElementPointer

class MixtrueListStateMachine {
  private val _mixtrueList = new MutableList[Mixtrue]()
  private val _elementPointer = new ElementPointer()
  private var _index = 0

  def += (area: Area): Unit = {
    if (_mixtrueList.length == 0) {
      val mixtrue = new Mixtrue(area)

      _mixtrueList += mixtrue

      _index = area.index
    }
  }
}
