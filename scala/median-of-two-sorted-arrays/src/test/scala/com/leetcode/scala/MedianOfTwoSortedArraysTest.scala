import _root_.org.scalatest._

import _root_.com.leetcode.scala.MedianOfTwoSortedArrays

class ExampleSpec extends FlatSpec with Matchers {
  it should "The median of [1, 3] and [2] should be 2" in {
    val median = new MedianOfTwoSortedArrays(Array(1, 3), Array(2)).median
    median should be (2)
  }
}
