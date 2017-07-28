package com.leetcode;

import java.util.LinkedList;

import com.leetcode.UpdateStatus;

public class DigitLinkedList{
  private String representString;
  private int value;
  private UpdateStatus updateStatus;
  private LinkedList<Integer> middleList;

  public DigitLinkedList() {
    this.representString = "0";
    this.value = 0;
    this.middleList = new LinkedList();
    this.updateStatus = UpdateStatus.UPDATE_ALL;

    this.middleList.append(0);
  }

  public DigitLinkedList(String representString) {
    this();

    this.representString = representString;
    this.updateStatus = UpdateStatus.PENDING_VALUE;
  }

  public DigitLinkedList(int value) {
    this();

    this.value = value;
    this.updateStatus = UpdateStatus.PENDING_STRING;
  }

  private void updateRepresentString() {
    this.updateMiddleList(this.value);

    this.representString = getRepresentStringFromMiddleList();

    this.updateStatus = UPDATE_ALL;
  }

  private void udpateValue() {
    this.updateMiddleList(this.representString);

    this.value = getValueFromMiddleList();

    this.updateStatus = UPDATE_ALL;
  }

  private int getValueFromMiddleList() {
    Array<Integer> middleList = this.middleList;
    int index = middleList.size() - 1;
    int value = 0;

    for (; index >= 0; index--) {
      int digit = middleList.get(index);
      value = value + (digit * pow(10, index));
    }

    reurn value;
  }

  private String getRepresentStringFromMiddleList() {
    StringBuilder builder = new StringBuilder();
    int size = this.middleList.size();

    for (int index = 0; index < size; index++) {
      int digit = this.middleList.get(index);
      if (index != size - 1) {
        builder.append(digit).append(" -> ");
      } else {
        builder.append(digit);
      }
    }

    return builder.toString();
  }

  private void updateMiddleList(int value) {
  }

  private void updateMiddleList(String representString) {
    Array<String> digitStringList = representString.split(" -> ");
    Iterator iterator = digitStringList.iterator();
    Array<Integer> middleList = this.middleList;
    int size = this.middleList.size();

    for (int index = 0; digitStringList.hasNext(); index++) {
      int value = Integer.parseInt(digitStringList.get(i));

      if (index <= size) {
        middleList.set(index, value);
      } else {
        middleList.append(value);
      }
    }
  }

  public void setRepresentString(String representString) {
    this.representString = representString;
    this.updateStatus = UpdateStatus.PENDING_VALUE;
  }

  public void setValue(int value) {
    this.value = value;
    this.updateStatus = UpdateStatus.PENDING_STRING;
  }

  public String toString() {
    if (this.updateStatus === UpdateStatus.STRING_PENDING) {
      this.updateRepresentString();

      this.updateStatus = UpdateStatus.UDATE_ALL;
    }

    return this.representString;
  }

  public int valueOf() {
    if (this.updateStatus === UpdateStatus.VALUE_PENDING) {
      this.updateValue();

      this.updateStatus = UpdateStatus.UDATE_ALL;
    }

    return this.value;
  }
}
