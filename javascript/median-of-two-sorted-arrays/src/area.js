import IllegalArgumentException from './illegal-argument-exception';
import NullSetException from './null-set-exception';
import Section from './section';

/**
 * 表示区间数据类
 * @class
 */
class Area {
  constructor(options = { arr: [], section: null }) {
    const { getInstances } = this.constructor;
    const { arr, section } = options;

    // 按顺序可配置和不可配置属性设置默认值
    Object.assign(this, options, getInstances());

    // 缓存数组长度
    this.arrLen = this.arr.length;

    // 如果只有`arr`变量,就根据其初始化`section`
    if (section == null) {
      this.initSection();
    } else {
      this.section = section;
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
      arrLen: 0,
    };
  }

  /**
   * @public
   * @method
   */
  initSection() {
    const { arr: { length } } = this;

    this.section = new Section({ start: 0, end: length - 1 });
  }

  /**
   * 返回区域首个元素
   * @public
   * @method
   */
  getHead() {
    const { arr, section } = this;
    const { start } = section;
    let head

    section.checkNull();
    if (start >= 0) {
      head = arr[start];
    } else {
      throw new InvalidArgumentException(
        'Area first element index should lesser and equals than zero;');
    }

    return head;
  }

  /**
   * 返回区域末尾元素
   * @public
   * @method
   */
  getTail() {
    const { arr, arrLen, section } = this;
    const { end } = section;
    let tail;

    section.checkNull();
    if (end < arrLen) {
      tail = arr[end];
    } else {
      throw new InvalidArgumentException(
        'Area last element index should lesser than arr length;');
    }

    return tail;
  }

  /**
   * 遍历区间
   * @public
   * @method
   */
  forEach(callback) {
    const { arr, section } = this;
    const { start, end } = section;

    try {
      section.checkNull();
    } catch (e) {
      return false;
    }

    for (let index = start; index <= end; index++) {
      callback(arr[index], index, arr);
    }
  }


  /**
   * 获取区间总长度
   * @public
   * @method
   */
  getTotal() {
    return this.section.length;
  }

  /**
   * 获取区间总长度
   * @public
   * @method
   */
  getClip(section =  null) {
    const { arr } = this;
    const area = new Area({ arr, section });

    return area;
  }
}

export default Area;
