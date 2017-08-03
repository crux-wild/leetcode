package com.leetcode.scala

import scala.math

import com.leetcode.scala.SectionCompare
import com.leetcode.scala.Area
import com.leetcode.scala.Section

class MedianOfTwoSortedArrays(val arr1: Array[Int], val arr2: Array[Int]) {
  private val _arr1 = arr1
  private val _arr2 = arr2
  private val _area1 = null
  private val _area2 = null

  private def initAreas(): Unit = {
    val len1 = _arr1.length
    val len2 = _arr2.length

    _area1 = new Area(index = 1, start = _arr1.head, end = _arr1.last, count = len1)
    _area2 = new Area(index = 2, start = _arr2.head, end = _arr2.last, count = len2)
  }

  private def mergeAreas(area1: Area, area2: Area): Unit = {
    val section1 = area1.section
    val section2 = area2.section

    Section.compareSection(section1, seciton2) match {
      case SectionCompare.LESSER =>
    }
  }

  def getMedian(): Double = {
    return 0
  }
}
