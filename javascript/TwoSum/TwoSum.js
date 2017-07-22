/**
 * @see https://leetcode.com/problems/two-sum/#/description
 */

var TwoSum;

/**
 * @constructor
 * @param {Array<Number>} numbers - 任意数字类型的数组
 * @param {Number} targer - 数字类型的数组任意两个数字求和的结果
 */
TwoSum = function TwoSumF(numbers, target) {
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

  numbers.forEach(function traversalNumbers(number1) {
    var number2 = target - number1;

    var frequencyOfNumber2 = frequencyOfNumbers[number2];

    // 两数相等
    if (number1 === number2) {
      // 并且该数字除去自身还存在一个
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


    // 处理两数相等的特殊情况
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
