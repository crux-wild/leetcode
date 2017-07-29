package com.leetcode;

import static java.lang.Math.pow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

import com.leetcode.UpdateStatus;

/**
 * @see https://leetcode.com/problems/add-two-numbers/tabs/description/
 *
 * ## 算法上下界
 *
 * |  算法      | constructor()     | getProp()          | setProp()           |
 * |------------|-------------------|--------------------|---------------------|
 * |  直接同步  | O(n)              | O(1)               | O(n)                |
 * |  需时同步  | O(1)              | O(1) ~ O(n)        | O(1)                |
 *
 * - 这里的`n`指的是更新的`representString`或者是`value`对应的链表长度
 * - 需时同步时`construcotr()`或是`setProp()`都只是更新实例属性，没有触发同步
 *
 * ## 摊还成本
 *
 */
public class DigitLinkedList{
  private String representString;
  private int value;
  private UpdateStatus updateStatus;
  private LinkedList<Integer> middleList;

  /**
   * O(1)
   */
  public DigitLinkedList() {
    this.representString = "0";
    this.value = 0;
    this.middleList = new LinkedList();
    this.middleList.add(0);
    this.updateStatus = UpdateStatus.UPDATE_ALL;
  }

  /**
   * O(n)
   * @param {String} representString - 用户传入字符串表征
   */
  public DigitLinkedList(String representString) {
    this();
    this.representString = representString;
    this.updateStatus = UpdateStatus.PENDING_VALUE;
  }

  /**
   * O(n)
   * @param {String} representString - 用户传入整数数值
   */
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
    String representString = this.representString;

    this.updateMiddleList(representString);
    this.value = this.getValueFromMiddleList();
    this.updateStatus = updateStatus.UPDATE_ALL;
  }

  private int getValueFromMiddleList() {
    LinkedList<Integer> middleList = this.middleList;
    int index = middleList.size() - 1;
    int sum = 0;

    for (; index >= 0; index--) {
      int digit = middleList.get(index);
      sum = (int)(sum + (digit * Math.pow(10, index)));
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

  /**
   * O(n)
   * @param {Integer} value - 用于更新中间链表的数值
   */
  private void updateMiddleList(int value) {
    // O(n)
    String valueString = Integer.toString(value);
    String[] digitStringArray = valueString.split("");
    List<String> digitStringList = new ArrayList();
    int index = digitStringArray.length - 1;

    // O(n)
    for (; index >= 0; index--) {
      digitStringList.add(digitStringArray[index]);
    }

    this.updateMiddleListFromStringList(digitStringList);
  }

  /**
   * O(n)
   * @param {String} representString - 用于更新中间链表的字符串表征
   */
  private void updateMiddleList(String representString) {
    // O(n)
    String[] digitStringArray = representString.split(" -> ");
    List<String> digitStringList = Arrays.asList(digitStringArray);

    this.updateMiddleListFromStringList(digitStringList);
  }

  /**
   * O(n)
   * @param {List<String>} digitStringList - 用于更新中间链表的字符串链表
   */
  private void updateMiddleListFromStringList(List<String> digitStringList) {
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

  /**
   * @param {String} representString - 用于更新的字符串表征
   */
  public void setRepresentString(String representString) {
    this.representString = representString;
    this.updateStatus = UpdateStatus.PENDING_VALUE;
  }

  /**
   * @param {Integer} value - 用于更新的整数数值
   */
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
