import _root_.org.scalatest.{ FlatSpec }

import _root_.com.leetcode.scala.{ StringToInteger }

class StringToIntegerSpec extends FlatSpec {
  behavior of "Leetcode  8. String to Integer (atoi)"

  it should "The \"100\"[String] should convert to 100[Int]" in {
    assert(new StringToInteger("100").value == 100)
  }
}
