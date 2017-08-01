package com.leetcode.scala;

import scala.math;

class Median(var start: Int, var end: Int) {
  private var _one = 0
  private var _two = 0

  def one = _one
  def two = _two

  def updateSection(section: Section): Unit = {
    start = section.start
    end = section.end
  }

  def updateSection(newStart: Int, newEnd: Int): Unit = {
    if (newStart <= newEnd)
      start = newStart
      end = newEnd
    //else
      // @TODO error handling
  }

  private def calculateMedian(): Unit = {
    val length = getLength()

    // 如果是偶数
    if (isEvenNumber(length)) {
      var pointer = (length / 2)
      _one = getIndex(pointer)
      _two = getIndex(pointer)
    }
    // 不是偶数
    else {
        var pointer = math.floor(length / 2).toInt;
        _one = getIndex(pointer)
        _two = getIndex(pointer + 1)
    }
  }

  private def getIndex(offset: Int): Int = {
    var index = start + offset;

    return index;
  }

  private def getLength(): Int = {
    var length = end - start;

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
