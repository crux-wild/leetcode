package com.leetcode.scala

class Section {
  private var _start = 0
  private var _end = 0

  def start = _start
  def start_= (newStart: Int): Unit = {
    if (newStart <= _end) {
      _start = newStart
    } else {
      throw new Exception("Start should be less than or equal end")
    }
  }

  def end = _end
  def end_= (newEnd: Int): Unit = {
    if (_start <= newEnd) {
      _end = newEnd
    } else {
      throw new Exception("End should be geater than or equal start")
    }
  }
}
