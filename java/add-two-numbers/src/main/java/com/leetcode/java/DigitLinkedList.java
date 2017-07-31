package com.leetcode.java;

import static java.lang.Math.pow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leetcode.java.UpdateStatus;

/**
 * 2.Add Two Numbers  实现算法
 * @see https://leetcode.com/problems/add-two-numbers/tabs/description/
 *
 * 具体时间复杂度分析链接
 * {@link} https://github.com/crux-wild/leetcode/blob/master/java/add-two-numbers/doc/timeComplexityAnalysis.md
 */
public class DigitLinkedList {
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
   * O(1)
   * @param {String} representString - 用户传入字符串表征
   */
  public DigitLinkedList(String representString) {
    this();

    // 防御型校验
    DigitLinkedList.checkDigitString(representString);
    this.representString = representString;
    this.updateStatus = UpdateStatus.PENDING_VALUE;
  }

  /**
   * O(1)
   * @param {String} representString - 用户传入整数数值
   */
  public DigitLinkedList(int value) {
    this();

    // 防御型校验
    DigitLinkedList.checkInteger(value);
    this.value = value;
    this.updateStatus = UpdateStatus.PENDING_STRING;
  }


  /**
   * O(1)
   * @param {String} representString
   */
  private static void checkDigitString(String representString) {
    String size = Integer.toString(Integer.SIZE - 1);
    String regex = "^([0-9]\\s->\\s){0,size}[0-9]{1}$";
    Pattern pattern;
    Matcher matcher;

    regex = regex.replaceAll("size", size);
    pattern = Pattern.compile(regex);
    matcher = pattern.matcher(representString);

    if (!matcher.matches()) {
      throw new IllegalArgumentException("Argument is not an digit string");
    }
  }

  /**
   * O(1)
   * @param {Integer} integer
   */
  private static void checkInteger(int integer) {
    // 不再最大最小范围内
    if (!(integer >= Integer.MIN_VALUE && integer <= Integer.MAX_VALUE)) {
      throw new IllegalArgumentException("Argument is not an integer");
    }
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

      if (this.isDigit(digit)) {
        sum = (int)(sum + (digit * Math.pow(10, index)));
      } else {
        continue;
      }
    }

    return sum;
  }

  private String getRepresentStringFromMiddleList() {
    StringBuilder builder = new StringBuilder();
    int size = this.middleList.size();

    for (int index = 0; index < size; index++) {
      int digit = this.middleList.get(index);

      // 存在借位情况
      if (this.isDigit(digit)) {
        if ((index != size - 1)) {
          builder.append(digit).append(" -> ");
        } else {
          builder.append(digit);
        }
      // 不存在借位情况
      } else {
        int lastIndex = builder.length();

        // 移除长度为4的" -> "字符串
        builder.delete(lastIndex - 4, lastIndex) ;
        break;
      }
    }

    System.out.println(builder.length());
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
   * O(1)
   * @param {Integer} integer
   */
  private boolean isDigit(int digit) {
    boolean flag = false;

    if (digit >= 0 && digit <= 9) {
      flag = true;
    }

    return flag;
  }

  /**
   * O(n)
   * @param {List<String>} digitStringList - 用于更新中间链表的字符串链表
   */
  private void updateMiddleListFromStringList(List<String> digitStringList) {
    LinkedList<Integer> middleList = this.middleList;
    int digitStringListSize = digitStringList.size();
    int middleListSize = this.middleList.size();
    int maxSize = Math.max(digitStringListSize, middleListSize);

    for (int index = 0; index < maxSize; index++) {
      if (index < digitStringListSize) {
        int digit = Integer.parseInt(digitStringList.get(index));

        if (index < middleListSize) {
          middleList.set(index, digit);
        } else {
          middleList.add(digit);
        }
      } else if (index >= digitStringListSize) {
        // digit的范围是[0, 9],超出范围的数字表示借位
        middleList.set(index, 10);
      }
    }
  }

  /**
   * @param {String} representString - 用于更新的字符串表征
   */
  public void setRepresentString(String representString) {
    // 防御型校验
    DigitLinkedList.checkDigitString(representString);

    this.representString = representString;
    this.updateStatus = UpdateStatus.PENDING_VALUE;
  }

  /**
   * @param {Integer} value - 用于更新的整数数值
   */
  public void setValue(int value) {
    // 防御型校验
    DigitLinkedList.checkInteger(value);

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
