/**
 * @file 1.Two Sum 的时间复杂度O(n)解决方案
 * @see https://leetcode.com/problems/two-sum/#/description
 */

/**
 * @constructor
 * @param {Array<Number>} numbers - 任意数字类型的数组
 * @param {Number} targer - 数字类型的数组任意两个数字求和的结果
 */
class TwoSum {
  constructor({ numbers = [], target = 0 }) {
    this.numbers = numbers;
    this.target = target;

    this.hashOfNumbers = {};
    // 可能存在多解用数组表示
    this.indices = [];

    this.initHashOfNumbers();
    this.initIndiecs();
  }

  /**
   * O(n)
   * @private
   * @method
   */
  initHashOfNumbers() {
    const { hashOfNumbers, numbers } = this;

    numbers.forEach((number, index) => {
      const slot = hashOfNumbers[number];

      if (slot === undefined) {
        // 对应槽位不存在，建立一个链表
        hashOfNumbers[number] = [index];
      } else {
        // 对应槽位存在，向链表添加元素
        slot.push(index);
      }
    });
  }

  /**
   * O(n)
   * @private
   * @method
   */
  initIndiecs() {
    const { hashOfNumbers, numbers, target } = this;

    const lastIndex = numbers.length - 1;

    numbers.forEach((number1, index1) => {
      // 为了使用`forEach`并保证结果，第n次循环可以直接略过
      if (index1 === lastIndex) {
        return;
      }

      const number2 = target - number1;
      const slotOfNumber2 = hashOfNumbers[number2] || 0;
      const frequencyOfNumber2 = slotOfNumber2.length;

      // 两数相等
      if (number1 === number2) {
        // 该数字除去自身意外还存在一个
        if (frequencyOfNumber2 >= 2) {
          this.appendIndices(index1, slotOfNumber2);
        }
      // 两数不相等
      } else if (frequencyOfNumber2 >= 1) {
        // 与number1求和为target的数字存在
        this.appendIndices(index1, slotOfNumber2);
      }
    });
  }

  /**
   * ## 时间复杂度
   *
   * ### 多解情况
   * O(1) ~ O(n)
   * 这个步骤的时间复杂度受槽位冲突程度影响，如果没有冲突就是O(1)
   *
   * ### 单解情况
   * O(1)
   * 由于索引数组是升序，如果是单解下面的循环至多循环2次(第一次是其本身)，所以，还
   * 常数级别的
   *
   * @private
   * @method
   * @param {Number} index1
   * @param {Array<Number>} arrayOfIndex2
   */
  appendIndices(index1, arrayOfIndex2) {
    arrayOfIndex2.forEach((index2) => {
      if (index2 > index1) {
        this.indices.push([index1, index2]);
      }
    });
  }

  /**
   * @public
   * @method
   * @return {Array<Number>} 与求和结果匹配的数组两个元素的下标
   */
  getIndices() {
    return this.indices;
  }
}

export default TwoSum;
