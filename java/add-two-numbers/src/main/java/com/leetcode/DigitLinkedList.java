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
  }

  private void udpateValue() {
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
