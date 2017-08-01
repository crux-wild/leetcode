package com.leetcode.scala

import scala.math

import com.leetcode.scala.AreaListStateMachine
import com.leetcode.scala.Section
import com.leetcode.scala.Area

class MedianOfTwoSortedArrays(val array1: Array[Int], val array2: Array[Int]) {
  private val _areaListStateMachine = new AreaListStateMachine()
  private val _array1 = array1
  private val _array2 = array2
  private val _length1 = array1.length
  private val _length2 = array2.length

  private def appendToAreaList(index: Int, start: Int, end: Int): Unit = {
    val section = new Section(start, end)

    _areaListStateMachine.areaList += new Area(index, section)
  }

  private def initAreaListStateMachine(): Unit = {
    val maxLength = math.max(_length1, _length2)

    if (maxLength == _length1) {
      appendToAreaList(index = 1, start = 0, end = _length1)
    } else {
      appendToAreaList(index = 2, start = 0, end = _length2)
    }
  }

  def getMedian(): Double = {
    return 0
  }
}
