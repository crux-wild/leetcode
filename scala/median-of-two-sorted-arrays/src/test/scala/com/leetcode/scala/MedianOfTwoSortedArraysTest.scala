import _root_.org.scalatest._

import _root_.com.leetcode.scala.MedianOfTwoSortedArrays

class MedianOfTwoSortedArraysSpec extends FlatSpec with Matchers {
  behavior of "The median of [2] and [1, 3] "

  it should "equals 2" in {
    val median = new MedianOfTwoSortedArrays(Array(2.0), Array(1.0, 3.0)).median
    assert(median === 2.0)
  }

  //behavior of "The median of [1, 3] and [2]"

  //it should "equals 2" in {
    //val median = new MedianOfTwoSortedArrays(Array(1.0, 3.0), Array(2.0)).median
    //assert(median === 2.0)
  //}

  //behavior of "The median of [1, 2] and [3, 4]"

  //it should "equals 2.5" in {
    //val median = new MedianOfTwoSortedArrays(Array(1, 2), Array(3, 4)).median
    //assert(median === 2.5)
  //}
}
