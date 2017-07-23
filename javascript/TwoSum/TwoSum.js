/**
 * @file 1.Two Sum 的时间复杂度O(n)解决方案
 * @see https://leetcode.com/problems/two-sum/#/description
 */

/**
 * @constructor
 * @param {Array<Number>} numbers - 任意数字类型的数组
 * @param {Number} targer - 数字类型的数组任意两个数字求和的结果
 */
function TwoSum(numbers, target) {
  // 避免`var twoSum = twoSum()`错误的初始化`window`
  if (!(this instanceof TwoSum)) {
    return new TwoSum();
  }

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
TwoSum.prototype.initHashOfNumbers = function initHashOfNumbersF() {
  var hashOfNumbers = this.hashOfNumbers;
  var numbers = this.numbers;
  var _this = this;

  numbers.forEach(function traversalNumbers(number) {
    var slot = HashOfNumbers[number];

    if (slot === void 0) {
      // 对应槽位不存在，建立一个链表
      slot = [number];
    } else {
      // 对应槽位存在，向链表添加元素
      slot.push(number);
    }
  });
}

/**
 * O(n)
 * @private
 * @method
 */
TwoSum.prototype.initIndiecs = function initIndiecsF() {
  var numbers = this.numbers;
  var target = this.target;
  var _this = this;

  numbers.forEach(function traversalNumbers(number1, index1) {
    var number2 = target - number1;
    var slotOfNumber2 = hashOfNumbers[number2] || 0;
    var frequencyOfNumber2 = slotOfNumber2.length;

    // 两数相等
    if (number1 === number2) {
      // 该数字除去自身意外还存在一个
      if (frequencyOfNumber2 >= 2) {
        _this.appendIndices(index1, slotOfNumber2);
      }
    // 两数不相等
    } else {
      // 与number1求和为target的数字存在
      if (frequencyOfNumber2 >= 1) {
        _this.appendIndices(index1, slotOfNumber2);
      }
    }
  });
}

/**
 * O(1)
 * @private
 * @method
 * @param {Number} index1
 * @param {Array<Number>} arrayOfIndex2
 */
TwoSum.prototype.appendIndices =
  function appendIndicesF(index1, arrayOfIndex2) {
    var _this = this;

    arrayOfIndex2.forEach(function traversalIndex2(index2) {
      if (index2 > index1) {
        _this.indices.push([index1, index2]);
      }
    });
}

/**
 * O(n)
 * @public
 * @method
 * @return {Array<Number>} 与求和结果匹配的数组两个元素的下标
 */
TwoSum.prototype.getIndices = function getIndicesF() {
  return this.indices;
}
