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
}

/**
 * @method
 * @return {Array<Number>} 与求和结果匹配的数组两个元素的下标
 */
TwoSum.prototype.getIndices = function getIndicesF() {
  var numbers = this.numbers;
  var target = this.target;

  var indices = [];

  return indices;
}
