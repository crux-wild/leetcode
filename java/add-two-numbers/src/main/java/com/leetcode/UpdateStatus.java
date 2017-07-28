package com.leetcode;

public enum UpdateStatus {
  UPDATEALL(0b00),
  STRING_PENDING(0b10),
  VALUE_PENDING(0b01);

  private int status;

  UpdateStatus(int status) {
    this.status = status;
  }

  public int valueOf() {
    return this.status;
  }
}

