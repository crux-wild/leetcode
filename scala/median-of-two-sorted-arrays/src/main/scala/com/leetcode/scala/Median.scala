package com.leetcode.scala;

import scala.math;

class Median(var start: Int, var end: Int) {
  private var _one = 0
  private var _two = 0

  def one = _one
  def two = _two

  def updateSection(var section: Section): Unit {
    start = section.start;
    end = section.end;
  }

  def updateSection(var newStart: Int, var newEnd: Int) {
    if newStart <= newEnd
      start = newStart
      end = newEnd
    else
      // @TODO error handling
  }

  private def calculateMedian(): Unit {
    val length = getLength();

    // 如果是偶数
    if (isEvenNumber(length))
      var pointer = length / 2;

      one = getIndex(pointer);
      two = getIndex(pointer);
    // 不是偶数
    else
      val pointer = math.floor(length / 2);

      one = getIndex(pointer);
      two = getIndex(pointer + 1);
  }

  private def getIndex(offset): Int {
    var index = start + offset;

    return index;
  }

  private def getLength(): Int {
    var length = end = start;

    return length;
  }

  private def isEvenNumber(val value: Int): Boolean {
    var flag = false;

    if ((value % 2) ==  0) {
      flag = true;
    }

    return flag;
  }
}
