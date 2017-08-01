package com.leetcode.scala;

import scala.math;

class Median(var start: Int, var end: Int) {
  private var _one = 0
  private var _two = 0

  def one = _one
  def one_= (newOne: Int): Unit = {
  }

  def two = _two
  def two_= (newValue: Int): Unit = {
  }

  private def calculateMedian() {
    val difference = end - start;

    // 如果是偶数
    if (isEven(difference))
      one = difference / 2;
      two = difference / 2;
    // 不是偶数
    else
      val floor = math.floor(difference / 2);

      one = floor;
      two = floor + 1;
  }

  private isEven(val value: Int): Boolean {
    var flag = false;

    if ((value % 2) ==  0) {
      flag = true;
    }

    return flag;
  }
}
