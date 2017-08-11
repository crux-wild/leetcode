import IllegalArgumentException from './illegal-argument-exception';
import NullSetException from './null-set-exception';

/**
 * 表示区间数据类
 * @class
 * @prop {Number} start - 区间初始化起点坐标
 * @prop {Number} end - 区间初始化结束坐标
 */
class Section {
  constructor(options = { start: NaN, end: NaN }) {
    const { getInstances } = this.constructor;

    // 按顺序可配置和不可配置属性设置默认值
    Object.assign(this, options, getInstances());

    this.updateLength();
  }

  /**
   * @private
   * @static
   * @method
   * @return {Object} 用户不能配置的实例属性默认值
   */
  static getInstances() {
    return {
      length: 0,
    };
  }

  /**
   * 根据传入参数初始化区间长度
   * @private
   * @method
   */
  updateLength() {
    const { start, end } = this;

    if (this.isNull()) {
      this.length = 0;
    } else {
      this.length = end - start + 1;
    }
  }

  /**
   * @public
   * @method
   */
  checkNull() {
    if (this.isNull()) {
      throw new NullSetException();
    }
  }

  /**
   * @public
   * @method
   */
  isNull() {
    const { start, end } = this;
    let flag = false;

    if (Number.isNaN(start) || Number.isNaN(end)) {
      flag = true;
    }

    return flag;
  }

  /**
   * @public
   * @method
   * @param {Number} start - 用于更新的开始坐标
   */
  setStart(start = 0) {
    if (start > end) {
      throw new IllegalArgumentException(
        `Argument start shouldn't greater end;`);
    } else {
      this.start = start;
      this.updateLength();
    }
  }

  /**
   * @public
   * @method
   * @param {Number} end - 用于更新的结束坐标
   */
  setEnd(end = 0) {
    if (end < start) {
      throw new IllegalArgumentException(
        `Argument end shouldn't lesser start;`);
    } else {
      this.end = end;
      this.updateLength();
    }
  }
}

export default Section;
