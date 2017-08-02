package com.leetcode.scala

import com.leetcode.scala.SectionCompare

class Section(val initStart: Int = 0, val initEnd: Int = 0) {
  private var _start = initStart
  private var _end = initEnd

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

object Section {
  def compareSection(
    section1: Section, section2: Section): SectionCompare.Value = {
    /**
     * |<-- section1 --->||<-- section2 --->|
     */
    if (section1.end <= section2.start) {
      return SectionCompare.LESSER
    }

    /**
     * |<-- section2 --->||<-- section1 --->|
     */
    if (section1.start >= section2.end) {
      return SectionCompare.GREATER
    }

    /**
     *   |<-- section1 -->|
     * |<--   section2   -->|
     */
    if (section1.start > section2.start && section1.end < section2.end) {
      return SectionCompare.BE_INCLUDED
    }

    /**
     * |<--   section1   -->|
     *   |<-- section2 -->|
     *
     */
    if (section1.start < section2.start && section1.end > section2.end) {
      return SectionCompare.INCLUDED
    }

    /**
     * |<-- section1 -->|
     * |<-- section2 -->|
     */
    if (section1.start == section2.start&& section1.end == section2.end) {
      return SectionCompare.EQUAL
    }


    /**
     * |<-- section1 -->|
     *            |<-- section2 -->|
     */
    if (section1.start <= section2.start && section1.end < section2.end
      && section1.end <= section2.start) {

      return SectionCompare.END_INCLUDED
    }

   /**
     *         |<-- section1 -->|
     * |<-- section2 -->|
     */
    if (section1.start >= section2.start && section2.end <= section1.end
      && section2.end < section1.start) {

      return SectionCompare.START_INCLUDED
    }

    return SectionCompare.NO_RELATIVE
  }
}
