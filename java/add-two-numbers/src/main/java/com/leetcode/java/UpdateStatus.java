package com.leetcode.java;

public enum UpdateStatus {
  UPDATE_ALL(0b11),
  PENDING_STRING(0b10),
  PENDING_VALUE(0b01);

  private int status;

  UpdateStatus(int status) {
    this.status = status;
  }

  public int getStatus() {
    return this.status;
  }
}

