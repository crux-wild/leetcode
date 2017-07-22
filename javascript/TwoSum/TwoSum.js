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
  this.indices = [0, 0];
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


    // @FIXME 控制跳转需要修改一下
    // 与number1求和为target的数字存在
    if (frequcyOfNumber2 >= 1) {
      _this.indiecs[0] = number1;
      _this.indiecs[1] = number2;
    }

    // 处理两数相等的特殊情况
    if (number1 === number2 && frequencyOfNumber2 >= 2) {
      _this.indiecs[0] = number1;
      _this.indiecs[1] = number1;
    }
  });
}

/**
 * @method
 * @return {Array<Number>} 与求和结果匹配的数组两个元素的下标
 */
TwoSum.prototype.getIndices = function getIndicesF() {
  var target = this.target;

  // 顺序排序
  this.numbers.sort();

  this.initFrequencyOfNumbers();

  this.initIndiecs();

  return this.indices;
}
