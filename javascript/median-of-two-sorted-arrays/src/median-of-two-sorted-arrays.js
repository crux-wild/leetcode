import MedianValueStatusMachine from './median-value-status-machine';
import IllegalArgumentException from './illegal-argument-exception';
import Section from './section';
import Area from './area';

/**
 * O(log(n)log(m))
 * 获取两个排序数组的中位数数值表征算法类
 * @see https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * @class
 */
class MedianOfTwoSortedArrays {
  constructor(options = { arr1: [], arr2: [] }) {
    const { getInstances } = this.constructor;
    const { arr1, arr2 } = options;

    // 按顺序可配置和不可配置属性设置默认值
    Object.assign(this, options, getInstances());

    this.total = arr1.length + arr2.length;
    this.initMedianValue();
    this.initMedian();
  }

  /**
   * @private
   * @static
   * @method
   * @return {Object} 用户不能配置的实例属性默认值
   */
  static getInstances() {
    return {
      medianValue: null,
      median: 0,
      total: 0,
    };
  }

  /**
   * 统计两个区域的总个数
   * @private
   * @method
   */
  static statisticsCountOfAreas(area1 = null, area2 = null) {
    const count = area1.getTotal() + area2.getTotal();

    return count;
  }

  /**
   * O(log(n))
   * 注: 其中n指的是被二分的区域的总个数
   *
   * 基于中位数和是否需要交集进行分割
   * @private
   * @method
   */
  static binarySplitArea(area = null, isIntersection = false) {
    const { section } = area;
    const { calculateMedian } = MedianValueStatusMachine;
    const { start, end } = section;
    const median = calculateMedian(start, end);
    const { one, two, count } = median;
    let before;
    let after;

    // 奇数个数
    if (count == 1) {
      // 需要交集
      if (isIntersection == true) {
        before = area.getClip(new Section({ start, end: one }))
      } else if(isIntersection == false) {
        before = area.getClip(new Section({ start, end: one - 1 }))
      }
    // 偶数个数
    } else if(count == 2) {
      // 需要交集
      if (isIntersection == true) {
        before = area.getClip(new Section({ start, end: one + 1 }))
      } else if(isIntersection == false) {
        before = area.getClip(new Section({ start, end: one }))
      }
    }

    after = area.getClip(new Section({ start: two, end }));

    return {
      before,
      after,
    };
  }

  /**
   * O(log(n))
   * 注: 其中n是指的查找边界的区域的总个数
   *
   * 基于中位数进行二分查找
   * @private
   * @method
   */
  static binarySearchArea(portion = 'before', areaClips, area) {
    const { binarySplitArea, checkPortion } = MedianOfTwoSortedArrays;
    let areaClip = area;

    checkPortion(portion);
    if (portion == 'before') {
      const tail = areaClips.before.getTail();

      while (areaClip.getTail() > tail) {
        if (areaClip.getTotal() <= 1) {
          // 循环到不可分,还是无法匹配,返回空集
          areaClip = areaClip.getClip(new Section(NaN, NaN));
          break;
        }

        areaClip = binarySplitArea(areaClip).before;
      }
    } else if (portion == 'after') {
      const head = areaClips.after.getHead();

      while (areaClip.getHead() < head) {
        if (areaClip.getTotal() <= 1) {
          // 循环到不可分,还是无法匹配,返回空集
          areaClip = areaClip.getClip(new Section(NaN, NaN));
          break;
        }

        areaClip = binarySplitArea(areaClip).after;
      }
    }

    return areaClip;
  }

  /**
   * @private
   * @static
   * @method
   */
  static isIndivisble(area = null) {
    let flag = false;

    if (area.getTotal() <= 2) {
      flag = true;
    }

    return flag;
  }

  /**
   * @private
   * @static
   * @method
   */
  static checkPortion(portion) {
    if (!(portion == 'before' || portion == 'after')) {
      throw new IllegalArgumentException('Argument portion should be one of' +
        'values in (before|after)');
    }
  }

  /**
   * 根据当前上下文获取遍历方向
   * @private
   * @static
   * @method
   */
  static getDirection(before, after) {
    let direction;

    if (before < 0 && after < 0) {
      throw new illegalargumentexception(
      `Argument before and after shouldn't both equal zero;`);
    } else if (before < 0) {
      direction = 'after';
    } else if (after < 0) {
      direction = 'before';
    } else {
      const max = Math.max(before, after);

      if (max == before) {
        direction = 'before';
      } else {
        direction = 'after';
      }
    }

    return direction;
  }

  /**
   * 解析两个不可分割区域
   * @private
   * @static
   * @method
   */
  processIndivisbleAreas(area1 = null, area2 = null, before = 0,
    after = 0, medianCount = 0) {

    const { getDirection } = this.constructor;
    const { medianValue } = this;
    const unionSet = [];

    area1.forEach((elem) => {
      unionSet.push(elem);
    });
    area2.forEach((elem) => {
      unionSet.push(elem);
    });

    /**
     * O(1)
     * 这里排序是元素个数小于4个情况, 所以是常数级别
     */
    unionSet.sort((a, b) => {
      if (a < b) {
        return -1;
      }
      if (a > b) {
        return 1;
      }

      return 0;
    });

    const direction = getDirection(before, after);

    // 从前遍历
    if (direction == 'after') {
      const length = unionSet.length;
      const end = length - 1 - after;
      const start = end - (medianCount - 1);

      for (let index = end; index >= start && index >= 0; index--) {
        const median = unionSet[index];

        medianValue.appendMedian(median);
      }
    }

    // 从后遍历
    if (direction == 'before') {
      const start = before;
      const end = start + medianCount - 1;

      for (let index = start; index <= end && index < unionSet.length; index++) {
        const median = unionSet[index];

        medianValue.appendMedian(median);
      }
    }
  }

  /**
   * 获取可分割区域
   * @private
   * @static
   * @method
   */
  static getDivisibleArea(area1 = null, area2 = null) {
    const { isIndivisble } = MedianOfTwoSortedArrays ;
    let divisbleArea;

    if (isIndivisble(area1) && isIndivisbleArea(area2)) {
      throw new illegalargumentexception(
      `the areas shouldn't both indivisble;`);
    } else if (isIndivisble(area1)) {
      divisbleArea = area2;
    } else {
      divisbleArea = area1;
    }

    return divisbleArea;
  }

  /**
   * @private
   * @static
   * @method
   */
  static getAnotherArea(area, area1, area2) {
    let anotherArea;

    if (area == area1) {
      anotherArea = area2;
    } else if (area == area2) {
      anotherArea = area1;
    }

    return anotherArea;
  }

  /**
   * @private
   * @static
   * @method
   */
  static getMedianCount(bound, count, surplus) {
    let medianCount = 0;

    if (surplus == 2) {
      if (count >= bound + 2) {
        medianCount = 2;
      }
    } else if (surplus == 1) {
      if (count >= bound + 1) {
        medianCount = 1;
      }
    }

    return medianCount;
  }

  /**
   * @private
   * @method
   */
  seekMedianInPortion(portion = 'before', areaClips = null,
    area = null, before = 0, after = 0, total = 0) {

    const surplus = this.medianValue.getSurplus();
    const { getMedianCount } = this.constructor;
    // 所有解已经求解
    if (surplus == 0) { return false; }

    const {
      statisticsCountOfAreas,
      binarySearchArea,
      checkPortion,
    } = this.constructor;

    const area2 = binarySearchArea(portion, areaClips, area);
    const area1 = areaClips[portion];
    const count = statisticsCountOfAreas(area1, area2);
    const anotherCount = total - count;
    let medianCount;

    checkPortion(portion);
    if (portion == 'before') {
      medianCount = getMedianCount(before, count, surplus);
      if (medianCount >= 0) {
        const newAfter = after - anotherCount;
        this.seekMedianInAreas(area1, area2, before, newAfter, medianCount);
      }
    } else if (portion == 'after') {
      medianCount = getMedianCount(before, count, surplus);
      if (medianCount >= 0) {
        const newBefore = before - anotherCount;
        this.seekMedianInAreas(area1, area2, newBefore , after, medianCount);
      }
    }
  }

  /**
   * @private
   * @static
   * @method
   */
  seekMedianInAreas(area1 = null, area2 = null, before = 0, after = 0,
    medianCount = 0) {
    const {
      getDivisibleArea,
      binarySplitArea,
      getAnotherArea,
      statisticsCountOfAreas,
    } = this.constructor;

    let divisbleArea;
    let total;

    try {
      divisbleArea = getDivisibleArea(area1, area2);
      total = statisticsCountOfAreas(area1, area2);
    } catch (e) {
      this.processIndivisbleAreas(area1, area2, before, after, medianCount);
      return false;
    }

    const areaClips = binarySplitArea(divisbleArea, true);
    const area = getAnotherArea(divisbleArea, area1, area2);

    this.seekMedianInPortion('before', areaClips, area, before, after, total);
    this.seekMedianInPortion('after', areaClips, area, before, after, total);
  }

  /**
   * 初始化中位数状态机
   * @private
   * @method
   */
  initMedianValue() {
    const { arr1, arr2 } = this;
    const { total } = this;

    this.medianValue = new MedianValueStatusMachine({ start: 1, end: total });
  }

  /**
   * O(log(n)log(m))
   *
   * 获取中位数除了对二分拆分和二分查找都是常数级别的
   * 其中二分拆分至多执行O(log(n))次对区域一进行拆分
   * 每次拆分之后，需要执行O(log(m))次二分查找对区域二进行拆分
   * 叠加起来的来说是O(log(n)log(m))
   *
   * 由于二分拆分进行的是深度优先遍历，如果一次遍历只找到一个解，
   * 需要回溯查找第二个至多是2O(log(n - 1)log(m)),忽略常数仍然是O(log(n)log(m))
   *
   * 计算中位数数值表征
   * @private
   * @method
   */
  initMedian() {
    const { arr1, arr2, total, medianValue } = this;
    const { median } = medianValue;
    const area1 = new Area({ arr: arr1 });
    const area2 = new Area({ arr: arr2 });
    const before = median.one - 1;
    const after = total - median.two;

    this.seekMedianInAreas(area1, area2, before, after, median.count);
    this.median = medianValue.calculateMedianValue();
  }

  /**
   * 获取算法运行实例中位数结果
   * @public
   * @method
   */
  getMedian() {
    return this.median;
  }
}

export default MedianOfTwoSortedArrays;
