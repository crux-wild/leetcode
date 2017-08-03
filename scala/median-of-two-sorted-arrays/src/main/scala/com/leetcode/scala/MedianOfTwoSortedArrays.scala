package com.leetcode.scala

import scala.math

import com.leetcode.scala.MixtrueListStateMachine
import com.leetcode.scala.Area
import com.leetcode.scala.Mixtrue

class MedianOfTwoSortedArrays(val arr1: Array[Int], val arr2: Array[Int]) {
  private val _mixtrueListStateMachine = new MixtrueListStateMachine()
  private val _arr1 = arr1
  private val _arr2 = arr2

  private def initMixtrueListStateMachine(): Unit = {
    val len1 = _arr1.length
    val len2 = _arr2.length
    val maxLen = math.max(len1, len2)

    // 选取区间范围较大的数组作为参照
    if (maxLen == len1) {
       _mixtrueListStateMachine +=
          new Area(index = 1, start = _arr1.head, end = _arr1.last, count = len1)
    } else {
       _mixtrueListStateMachine +=
          new Area(index = 2, start = _arr2.head, end = _arr2.last, count = len2)
    }
  }

  def getMedian(): Double = {
    return 0
  }
}
