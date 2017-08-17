import _root_.org.scalatest.{ FlatSpec }

import _root_.com.leetcode.scala.{ StringToInteger }

class StringToIntegerSpec extends FlatSpec {
  behavior of "Leetcode  8. String to Integer (atoi): [BCD case]"

  it should "The \"100\"[String] should convert to 100[Int]" in {
    assert(new StringToInteger("100").value == 100)
  }

  //behavior of "Leetcode  8. String to Integer (atoi): [OCT case]"

  //it should "The \"0123\"[String] should convert to 83[Int]" in {
    //assert(new StringToInteger("0123").value == 83)
  //}

  //behavior of "Leetcode  8. String to Integer (atoi): [Hex case]"

  //it should "The \"0xfff\"[String] should convert to 4905[Int]" in {
    //assert(new StringToInteger("0xfff").value == 4095)
  //}

  //behavior of "Leetcode  8. String to Integer (atoi): [E notation case]"

  //it should "The \"44e2\"[String] should convert to 4400[Int]" in {
    //assert(new StringToInteger("44e2").value == 4400)
  //}
}
