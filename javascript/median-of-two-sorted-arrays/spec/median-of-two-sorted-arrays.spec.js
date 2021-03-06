/**
 * @file 验证求解两个排序数组中位数的单元测试
 * @see https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 */

import test from 'ava';

import MedianOfTwoSortedArrays from 'median-of-two-sorted-arrays/src/median-of-two-sorted-arrays';

// 测试包含相等的数字
test('Leetcode Problems: 4. Median of Two Sorted Arrays: [contain equals number]', (it) => {
  const arr1 = [-33.33, 33.33, 33.33];
  const arr2 = [33.33, 33.33, 66.66];

  const givenValue = 33.33;

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  it.is(median, givenValue, 'The median of the two sorted array should be equal to' +
    `given value{${givenValue}};`);
});

// 测试浮点数
test('Leetcode Problems: 4. Median of Two Sorted Arrays: [floating number]', (it) => {
  const arr1 = [-33.33, -22.22, -11.10];
  const arr2 = [-5.5, 99.99, 222.22, 333.33, 999.99];

  const givenValue = 47.245;

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  it.is(median, givenValue, 'The median of the two sorted array should be equal to' +
    `given value{${givenValue}};`);
});

// 测试间隔间隔较大的情况
test('Leetcode Problems: 4. Median of Two Sorted Arrays: [gap is large]', (it) => {
  const arr1 = [-10000000, 1200020];
  const arr2 = [-10, -2, 5, 11, 1000];

  const givenValue = 5;

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  it.is(median, givenValue, 'The median of the two sorted array should be equal to' +
    `given value{${givenValue}};`);
});

// 测试包含负数的情况
test('Leetcode Problems: 4. Median of Two Sorted Arrays: [minus]', (it) => {
  const arr1 = [-100, -3, -4];
  const arr2 = [-2, 5, 11];

  const givenValue = -2.5;

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  it.is(median, givenValue, 'The median of the two sorted array should be equal to' +
    `given value{${givenValue}};`);
});

// 测试标准测用例
test('Leetcode Problems: 4. Median of Two Sorted Arrays: [standard case one]', (it) => {
  const arr1 = [1, 3];
  const arr2 = [2];

  const givenValue = 2;

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  it.is(median, givenValue, 'The median of the two sorted array should be equal to' +
    `given value{${givenValue}};`);
});

test('Leetcode Problems: 4. Median of Two Sorted Arrays: [standard case two]', (it) => {
  const arr1 = [1, 2];
  const arr2 = [3, 4];

  const givenValue = 2.5;

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  it.is(median, givenValue, 'The median of the two sorted array should be equal to' +
    `given value{${givenValue}};`);
});

// 测试标准测用例
test('Leetcode Problems: 4. Median of Two Sorted Arrays: [standard case three]', (it) => {
  const arr1 = [1, 3];
  const arr2 = [2];

  const givenValue = 2;

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  it.is(median, givenValue, 'The median of the two sorted array should be equal to' +
    `given value{${givenValue}};`);
});

