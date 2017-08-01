package com.leetcode.scala

class Section(val start: Int = 0, val end: Int = 0) {
  private var _start = start
  private var _end = end

  def start_= (newStart: Int): Unit = {
    if (newStart <= _end) {
      _start = newStart
    } else {
      throw new Exception("Start should be less than or equal end")
    }
  }

  def end_= (newEnd: Int): Unit = {
    if (_start <= newEnd) {
      _end = newEnd
    } else {
      throw new Exception("End should be geater than or equal start")
    }
  }
}
