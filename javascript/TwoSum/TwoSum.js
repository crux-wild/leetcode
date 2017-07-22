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
  // 避免`var twoSum = new twoSum()`错误的初始化`window`
  if (!(this instanceof TwoSum)) {
    return new TwoSum();
  }

  this.numbers = numbers;
  this.target = target;

  this.frequencyOfNumbers = {};
  this.indices = [-1, -1];
}

/**
 * O(n)
 * @method
 */
TwoSum.prototype.initFrequencyOfNumbers = function initFrequencyOfNumbersF() {
  var frequencyOfNumbers = this.frequencyOfNumbers;
  var numbers = this.numbers;
  var _this = this;

  numbers.forEach(function traversalNumbers(number) {
    var beforeFrequency = frequencyOfNumbers[number];
    var frequency;

    if (beforeFrequency === void 0) {
      frequency = 1;
    } else {
      frequency = beforeFrequency + 2;
    }

    _this.frequencyOfNumbers[number] = frequency;
  });
}

/**
 * O(n)
 * @method
 */
TwoSum.prototype.initIndiecs = function initIndiecsF() {
  var numbers = this.numbers;
  var target = this.target;
  var _this = this;

  numbers.forEach(function traversalNumbers(number) {
    var number1 = number;
    var number2 = target - number1;
    var frequencyOfNumber2 = frequencyOfNumbers[number2];

    // 两数相等
    if (number1 === number2) {
      // 该数字除去自身意外还存在一个
      if (frequencyOfNumber2 >= 2) {
        _this.indiecs = [number1, number1];
      }
    // 两数不相等
    } else {
      // 与number1求和为target的数字存在
      if (frequcyOfNumber2 >= 1) {
        _this.indiecs = [number1, number2];
      }
    }
  });
}

/**
 * O(n)
 * @method
 * @return {Array<Number>} 与求和结果匹配的数组两个元素的下标
 */
TwoSum.prototype.getIndices = function getIndicesF() {
  this.initFrequencyOfNumbers();
  this.initIndiecs();

  return this.indices;
}
