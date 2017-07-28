import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.leetcode.DigitLinkedList;

public class DigitLinkedListTest {
  @Test
  public void checkSumOfTwoNumbers() {
    DigitLinkedList digitLinkedList1 = new DigitLinkedList("2 -> 4 -> 3");
    DigitLinkedList digitLinkedList2 = new DigitLinkedList("5 -> 6 -> 4");

    assertEquals(digitLinkedList1.getValue(), 342);
    assertEquals(digitLinkedList2.getValue(), 465);

    int sum = digitLinkedList1.getValue() + digitLinkedList2.getValue();
    assertEquals(sum, 807);

    //digitLinkedList1.setValue(sum);

    //assertEquals(digitLinkedList1.getRepresentString(), "7 -> 0 -> 8");
  }
}
