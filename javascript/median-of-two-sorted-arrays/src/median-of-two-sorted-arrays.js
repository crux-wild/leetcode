import MedianValueStatusMachine from './median-value-status-machine';
import IllegalArgumentException from './illegal-argument-exception';
import Section from './section';
import Area from './area';

/**
 * 获取两个排序数组的中位数数值表征算法类
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
   * @private
   * @method
   */
  static statisticsCountOfAreas(area1 = null, area2 = null) {
    const count = area1.getTotal() + area2.getTotal();

    return count;
  }

  /**
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
          areaClip = areaClip.getClip(new Section(NaN, NaN));
          break;
        }

        areaClip = binarySplitArea(areaClip).before;
      }
    } else if (portion == 'after') {
      const head = areaClips.after.getHead();

      while (areaClip.getHead() < head) {
        if (areaClip.getTotal() <= 1) {
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
   * @private
   * @static
   * @method
   */
  processIndivisbleAreas(area1 = null, area2 = null, before = 0,
    after = 0) {

    const { getDirection } = this.constructor;
    const { medianValue } = this;
    const surplus = medianValue.getSurplus();
    const unionSet = [];

    area1.forEach((elem) => {
      unionSet.push(elem);
    });
    area2.forEach((elem) => {
      unionSet.push(elem);
    });

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
      const start = end - (surplus - 1);

      for (let index = end; index >= start; index--) {
        const median = unionSet[index];

        medianValue.appendMedian(median);
      }
    }

    // 从后遍历
    if (direction == 'before') {
      const start = before;
      const end = start + surplus - 1;

      for (let index = start; index <= end && index <unionSet.length; index++) {
        const median = unionSet[index];

        medianValue.appendMedian(median);
      }
    }
  }

  /**
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
   * @method
   */
  seekMedianInPortion(portion = 'before', areaClips = null,
    area = null, before = 0, after = 0, total = 0) {

    const surplus = this.medianValue.getSurplus();
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

    checkPortion(portion);
    if (portion == 'before') {
      if (count >= before + 1) {
        this.seekMedianInAreas(area1, area2, before, after - anotherCount);
      }
    } else if (portion == 'after') {
      if (count >= after + 1) {
        this.seekMedianInAreas(area1, area2, before - anotherCount, after);
      }
    }
  }

  /**
   * @private
   * @static
   * @method
   */
  seekMedianInAreas(area1 = null, area2 = null, before = 0, after = 0) {
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
      this.processIndivisbleAreas(area1, area2, before, after);
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

    this.seekMedianInAreas(area1, area2, before, after);
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
