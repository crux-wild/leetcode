package com.leetcode.scala

import scala.math

class Median(val start: Int = 0, val end: Int = 0) {
  private var _one = 0
  private var _two = 0
  private var _start = start
  private var _end = end

  def one = _one
  def two = _two

  def updateSection(section: Section): Unit = {
    _start = section.start
    _end = section.end
  }

  def updateSection(newStart: Int, newEnd: Int): Unit = {
    if (newStart <= newEnd) {
      _start = newStart
      _end = newEnd
    } else {
      throw new Exception("End should be geater start")
    }
  }

  private def calculateMedian(): Unit = {
    val length = getLength()

    // 如果是偶数
    if (isEvenNumber(length)) {
      var offset = (length / 2)
      _one = getIndex(offset)
      _two = getIndex(offset)
    }
    // 不是偶数
    else {
        var offset = math.floor(length / 2).toInt
        _one = getIndex(offset)
        _two = getIndex(offset + 1)
    }
  }

  private def getIndex(offset: Int): Int = {
    val index = start + offset;

    return index;
  }

  private def getLength(): Int = {
    val length = end - start;

    return length;
  }

  private def isEvenNumber(value: Int): Boolean = {
    var flag = false;

    if ((value % 2) ==  0) {
      flag = true;
    }

    return flag;
  }
}
