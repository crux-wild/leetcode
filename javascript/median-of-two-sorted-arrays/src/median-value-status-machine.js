import IllegalArgumentException from './illegal-argument-exception';

/**
 * 求解中位数数值表征的状态机
 * @class
 */
class MedianValueStatusMachine {
  constructor(options = { start: 0, end: 0 }) {
    const { getInstances } = this.constructor;

    // 按顺序可配置和不可配置属性设置默认值
    Object.assign(this, options, getInstances());

    this.initInstance();
  }

  /**
   * @private
   * @static
   * @method
   * @return {Object} 用户不能配置的实例属性默认值
   */
  static getInstances() {
    return {
      median: null,
      total: 0,
      resolved: 0,
      medianList: [],
    };
  }

  /**
   * @public
   * @static
   * @method
   * @param {Number} 开始元素坐标
   * @param {Number} 结束元素坐标
   * @param {Object} 中位数计算结果
   */
  static calculateMedian(start = 0, end = 0) {
    if (start > end) {
      throw new InvalidArgumentException(
        'Agrument start shouldn lesser than end;');
    }

    const length = end - start + 1;
    const offset = Math.ceil(length / 2);
    const one = start + offset -1;
    let count;
    let two;

    // 长度为偶数
    if (length % 2 == 0) {
      two = one + 1;
      count = 2;
    // 长度为基数
    } else {
      two = one;
      count = 1;
    }

    return { one, two, count };
  }

  /**
   * 根据输入初始化中位数总量
   * @private
   * @method
   */
  initInstance() {
    const { calculateMedian } = this.constructor;
    const { start, end } = this;
    const median = calculateMedian(start, end);

    // 缓存中位数运算结果
    this.median = median;
    this.total = median.count;
  }

  /**
   * 状态机中添加一个已获取的中位数
   * @public
   * @method
   */
  appendMedian(median = 0) {
    const { resolved, total, medianList } = this;

    if (resolved + 1 > total) {
      throw new IllegalArgumentException(
        `Current median count shouldn't lesser than total{${total}};`);
    } else {
      this.resolved = resolved + 1;
      medianList.push(median);
    }
  }

  /**
   * 获取中位数的剩余个数
   * @public
   * @method
   */
  getSurplus() {
    const { total, resolved } = this;

    return total - resolved;
  }

  /**
   * 获取中位数的数值表征
   * @public
   * @method
   */
  calculateMedianValue() {
    const { resolved, total, medianList } = this;
    let medianValue;

    if (resolved < total) {
      throw new IllegalArgumentException(
        `Median count should equals {${total}} when get median value;`);
    }

    if (total == 1) {
      const [one] = medianList
      medianValue = one;
    } else {
      const [one, two] = medianList;
      medianValue = (one + two) / 2;
    }

    return medianValue;
  }
}

export default MedianValueStatusMachine;
