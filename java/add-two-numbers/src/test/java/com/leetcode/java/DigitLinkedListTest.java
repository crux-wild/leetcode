package com.leetcode.java;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.leetcode.java.DigitLinkedList;

public class DigitLinkedListTest {
  @Test
  public void checkSumOfTwoNumbers() {
    DigitLinkedList digitLinkedList1 = new DigitLinkedList("2 -> 4 -> 3");
    DigitLinkedList digitLinkedList2 = new DigitLinkedList("5 -> 6 -> 4");

    // 验证字符串表征转换是整数正确性
    assertEquals(digitLinkedList1.getValue(), 342);
    assertEquals(digitLinkedList2.getValue(), 465);

    // 验证结合结果
    int sum = digitLinkedList1.getValue() + digitLinkedList2.getValue();
    assertEquals(sum, 807);

    // 验证求和结果表征字符串
    digitLinkedList1.setValue(sum);
    assertEquals(digitLinkedList1.getRepresentString(), "7 -> 0 -> 8");
  }
}
