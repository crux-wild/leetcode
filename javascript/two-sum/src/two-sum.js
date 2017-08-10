/**
 * @file 1.Two Sum 数字重复较小情况下时间复杂度O(n)解决方案
 * @see https://leetcode.com/problems/two-sum/#/description
 */
import InvalidArgumentException from './invalid-argument-exception';
import ChainedHashTable from './chained-hash-table';

/**
 * @class
 * @prop {Array<Number>} numbers - 任意数字类型的数组
 * @prop {Number} targer - 数字类型的数组任意两个数字求和的结果
 * @prop {Boolean} isMultiple -输出结果是否需要多解
 */
class TwoSum {
  constructor(options = { number: [], target: 0, isMultiple: true }) {
    const { getInstances } = this.constructor;

    // 按顺序可配置和不可配置属性设置默认值
    Object.assign(this, options, getInstances());

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
      hashOfNumbers: new ChainedHashTable(),
      /**
       * @default
       * 可能存在多解用数组表示
       */
      indices: [],
    };
  }

  /**
   * O(1)
   * @private
   * @method
   * @return {Boolean} 这组数组是否复合要求
   */
  isOneOfIndices(number1 = 0, number2 = 0) {
    const { hashOfNumbers } = this;
    const listOfNumber2 = hashOfNumbers.search({ key: number2 });
    let flag = false;

    if (listOfNumber2.length >= 1) {
      flag = true;
    }

    return flag;
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
        hashOfNumbers.insert({ key: number1, value: index1 });
      }

      return false;
    });
  }

  /**
   * ## 时间复杂度
   *
   * ### 多解情况
   * O(1) ~ O(n)
   * 这个步骤的时间复杂度受槽位冲突程度影响，如果没有冲突就是O(1)。
   * 冲突所带来的时间复杂度变化，本质上是由于解的个数增加，而不是算法本身
   *
   * ### 单解情况
   * O(1)
   *
   * @private
   * @method
   * @param {Number} index1
   * @param {Number} number2
   */
  appendIndices(index1 = 0, number2 = 0) {
    const { isMultiple, hashOfNumbers } = this;
    const listOfNumber2 = hashOfNumbers.search({ key: number2 });

    listOfNumber2.some((index2) => {
      /**
       *
       * 从前向后遍历
       * <------------ *
       * n1, n2, ..., ptr
       */
      if (index2 < index1) {
        // 需要多解
        this.indices.push([index1, index2]);
        hashOfNumbers.delete({ key: number2, value: index2  });

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
