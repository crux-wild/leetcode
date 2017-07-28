/**
 * @file 1.Two Sum 时间复杂度O(n)解决方案
 * @see https://leetcode.com/problems/two-sum/#/description
 */

/**
 * 用户输入的参数无法返回一个解
 * @private
 * @class
 */
class InvalidArgumentException extends Error {
  constructor(message = 'No two sum solution') {
    super(message);
  }
}

/**
 * @class
 * @prop {Array<Number>} numbers - 任意数字类型的数组
 * @prop {Number} targer - 数字类型的数组任意两个数字求和的结果
 * @prop {Boolean} isMultiple -输出结果是否需要多解
 */
class TwoSum {
  constructor(options = {}) {
    const { getOptions, getInstances } = this.constructor;

    // 按顺序可配置和不可配置属性设置默认值
    Object.assign(this, getOptions(options), getInstances());

    //this.initHashOfNumbers();
    this.initIndiecs();

    if (this.indices.length === 0) {
      throw new InvalidArgumentException();
    }
  }

  /**
   * @private
   * @static
   * @method
   * @return {Object} 用户不能配置的实例属性默认值
   */
  static getInstances() {
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
   * @return {Object} 如果默认配置和用户配置生成配置项
   */
  static getOptions(options) {
    return Object.assign({
      numbers: [],
      target: 0,
      isMultiple: true,
    }, options);
  }

  /**
   * O(1)
   * @private
   * @method
   * @return {Boolean} 这组数组是否复合要求
   */
  isOneOfIndices(number1 = 0, number2 = 0) {
    const frequencyOfNumber2 = this.hashOfNumbers[number2];
    let flag = false;

    if (Array.isArray(frequencyOfNumber2) && frequencyOfNumber2.length >= 1) {
      flag = true;
    }

    return flag;
  }

  /**
   * O(1)
   * @private
   * @method
   */
  appendItemToHashOfNumbers(number, index) {
    const { hashOfNumbers } = this;

    const slot = hashOfNumbers[number];

    if (slot === undefined) {
      // 对应槽位不存在，建立一个链表
      hashOfNumbers[number] = [index];
    } else {
      // 对应槽位存在，向链表添加元素
      slot.push(index);
    }
  }

  /**
   *  外层循环固定为O(n)，时间复杂度主要取决于`appendIndices`执行时间
   * O(n) ~ O(n^2)
   * @private
   * @method
   */
  initIndiecs() {
    const { numbers, target, hashOfNumbers, isMultiple } = this;

    numbers.some((number1, index1) => {
      const number2 = target - number1;

      if (this.isOneOfIndices(number1, number2)) {
        this.appendIndices(index1, number2);

        if (!isMultiple) {
          return true;
        }
      } else {
        this.appendItemToHashOfNumbers(number1, index1);
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
   * @param {Number} number2
   */
  appendIndices(index1 = 0, number2 = 0) {
    const { isMultiple, hashOfNumbers } = this;
    const arrayOfIndex2 = hashOfNumbers[number2];

    arrayOfIndex2.some((index2) => {
      /**
       *
       * 从前向后遍历
       * <------------ *
       * n1, n2, ..., ptr
       */
      if (index2 < index1) {
        // 需要多解
        this.indices.push([index1, index2]);
        arrayOfIndex2.shift();

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
   * @throws {InvalidArgumentException}
   * @return {Array<Number>} 与求和结果匹配的数组两个元素的下标
   */
  getIndices() {
    return this.indices;
  }
}

export default TwoSum;
