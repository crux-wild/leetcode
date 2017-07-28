package com.leetcode;

import static java.lang.Math.pow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

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

    this.middleList.add(0);
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

    this.updateStatus = updateStatus.UPDATE_ALL;
  }

  private void updateValue() {
    this.updateMiddleList(this.representString);

    this.value = this.getValueFromMiddleList();

    this.updateStatus = updateStatus.UPDATE_ALL;
  }

  private int getValueFromMiddleList() {
    LinkedList<Integer> middleList = this.middleList;
    int index = middleList.size() - 1;
    int sum = 0;

    for (; index >= 0; index--) {
      int digit = middleList.get(index);
      value = (int)(value + (digit * Math.pow(10, index)));
    }

    return sum;
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
    String valueString = Integer.toString(value);
    String[] digitStringArray = valueString.split("");
    List<String> digitStringList = new ArrayList();
    int index = digitStringArray.length - 1;

    for (; index >= 0; index--) {
      digitStringList.add(digitStringArray[index]);
    }

    this.updateMiddleListFromStringArray(digitStringList);
  }

  private void updateMiddleList(String representString) {
    List<String> digitStringList = Arrays.asList(representString.split(" -> "));

    this.updateMiddleListFromStringArray(digitStringList);
  }

  private void updateMiddleListFromStringArray(List<String> digitStringList) {

    LinkedList<Integer> middleList = this.middleList;
    int digitStringListSize = digitStringList.size();
    int middleListSize = this.middleList.size();

    for (int index = 0; index < digitStringListSize; index++) {
      int value = Integer.parseInt(digitStringList.get(index));

      if (index < middleListSize) {
        middleList.set(index, value);
      } else {
        middleList.add(value);
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

  public String getRepresentString() {
    if (this.updateStatus == UpdateStatus.PENDING_STRING) {
      this.updateRepresentString();

      this.updateStatus = UpdateStatus.UPDATE_ALL;
    }

    return this.representString;
  }

  public int getValue() {
    if (this.updateStatus == UpdateStatus.PENDING_VALUE) {
      this.updateValue();

      this.updateStatus = UpdateStatus.UPDATE_ALL;
    }

    return this.value;
  }
}
