import test from 'ava';

import MedianOfTwoSortedArrays from 'median-of-two-sorted-arrays/src/median-of-two-sorted-arrays';

// 测试多解情况
test('Leetcode Problems: 4. Median of Two Sorted Arrays;', (it) => {
  const arr1 = [1, 3, 4, 100];
  const arr2 = [2, 5, 11];

  const medianOfTwoSortedArrays = new MedianOfTwoSortedArrays({ arr1, arr2 });
  const median = medianOfTwoSortedArrays.getMedian();

  // 索引对应的两个数加和应该等于`target`
  it.deepEqual(median, 4,
    'Indices of the two numbers such that they add up to a specific target;');
});

// 测试单解情况
