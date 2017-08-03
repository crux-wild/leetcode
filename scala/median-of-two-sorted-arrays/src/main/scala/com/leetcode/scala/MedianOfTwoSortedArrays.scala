package com.leetcode.scala

import scala.math

import com.leetcode.scala.MixtrueListStateMachine
import com.leetcode.scala.Section
import com.leetcode.scala.Area

class MedianOfTwoSortedArrays(val array1: Array[Int], val array2: Array[Int]) {
  private val _areaListStateMachine = new MixtrueListStateMachine()
  private val _array1 = array1
  private val _array2 = array2

  private def initToMergeAreaListStateMachine(): Unit = {
    val length1 = _array1.length
    val length2 = _array2.length
    val maxLength = math.max(length1, length2)

    if (maxLength == length1) {

    } else {

    }
  }

  def getMedian(): Double = {
    return 0
  }
}
