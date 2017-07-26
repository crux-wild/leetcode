/**
 * @file 1.Two Sum 数字重复较小情况下时间复杂度O(n)解决方案
 * @see https://leetcode.com/problems/two-sum/#/description
 */

/**
 * @class
 * @prop {Array<Number>} numbers - 任意数字类型的数组
 * @prop {Number} targer - 数字类型的数组任意两个数字求和的结果
 * @prop {Boolean} isMultiple -输出结果是否需要多解
 */
class TwoSum {
  constructor(options = {}) {
    const { getDefaultOptions, getDefaultInstances } = this.constructor;

    // 按顺序可配置和不可配置属性设置默认值
    Object.assign(this, getDefaultOptions(), options, getDefaultInstances());

    this.initHashOfNumbers();
    this.initIndiecs();
  }

  /**
   * @private
   * @static
   * @method
   * @return {Object} 用户不能配置的实例属性默认值
   */
  static getDefaultInstances() {
    return {
      hashOfNumbers: {},
      /**
       * @default
       * 可能存在多解用数组表示
       */
      indices: [],
    };
  }

  /**
   * @private
   * @static
   * @method
   * @return {Object} 如果用户没有传参的默认配置
   */
  static getDefaultOptions() {
    return {
      numbers: [],
      target: 0,
      isMultiple: true,
    };
  }

  /**
   * O(1)
   * @private
   * @static
   * @method
   * @return {Boolean} 这组数组是否复合要求
   */
  static isOneOfIndices({ number1 = 0, number2 = 0, frequencyOfNumber2 = 0 }) {
    // 两数相等
    if (number1 === number2) {
      // 该数字除去自身意外还存在一个
      if (frequencyOfNumber2 >= 2) {
        return true;
      }
    // 两数不相等
    } else if (frequencyOfNumber2 >= 1) {
      // 与number1求和为target的数字存在
      return true;
    }

    return false;
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
   *  外层循环固定为O(n)，时间复杂度主要取决于`appendIndices`执行时间
   * O(n) ~ O(n^2)
   * @private
   * @method
   */
  initIndiecs() {
    const { numbers, target, hashOfNumbers, isMultiple } = this;
    const lastIndex = numbers.length - 1;

    numbers.some((number1, index1) => {
      // 第n个元素后面没有元素，不需要次循环
      if (index1 === lastIndex) {
        return true;
      }

      const number2 = target - number1;
      const slotOfNumber2 = hashOfNumbers[number2] || 0;
      const frequencyOfNumber2 = slotOfNumber2.length;

      if (TwoSum.isOneOfIndices({ number1, number2, frequencyOfNumber2 })) {
        this.appendIndices(index1, slotOfNumber2);

        if (!isMultiple) {
          return true;
        }
      }

      return false;
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
  appendIndices(index1 = 0, arrayOfIndex2 = []) {
    const { isMultiple } = this;

    arrayOfIndex2.some((index2) => {
      if (index2 > index1) {
        // 需要多解
        this.indices.push([index1, index2]);

        // 不需要多解
        if (!isMultiple) {
          // 找到一组解就可以停止循环
          return true;
        }
      }

      return false;
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
