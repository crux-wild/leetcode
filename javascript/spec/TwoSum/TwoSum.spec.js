import test from 'ava';

import getSourceFile from '../../lib/tools';

const TwoSum = getSourceFile(__filename);

test('Leetcode Problems: 1.Two Sum[multiple solutions];', (it) => {
  // 自定义测试数据
  const twoSum = new TwoSum({
    numbers: [1, 2, 3, 4, 5, 6],
    target: 7,
    isMultiple: true,
  });

  const indices = twoSum.getIndices();
  const indicesCount = indices.length;

    // 如果只需要求解一个问题，那么解的个数应该在[0, )区间内
  it.true((indicesCount >= 0),
    'indicesCount shoule located within the interval [0, );');

  // 验证算法运行结果
  indices
    .forEach((indice) => {
      const { numbers, target } = twoSum;
      const [index1, index2] = indice;
      const number1 = numbers[index1];
      const number2 = numbers[index2];

      // 索引对应的两个数加和应该等于`target`
      it.deepEqual(number1 + number2, target,
      'Indices of the two numbers such that they add up to a specific target;');
    });
});

test('Leetcode Problems: 1.Two Sum[single solution];', (it) => {
  // 自定义测试数据
  const twoSum = new TwoSum({
    numbers: [1, 2, 3, 4, 5, 6],
    target: 7,
    isMultiple: false,
  });

  let indices = twoSum.getIndices();
  const indicesCount = indices.length;

    // 如果只需要求解一个问题，那么解的个数应该在[0, 1]区间内
  it.true((indicesCount >= 0 && indicesCount <= 1),
    'indicesCount shoule located within the interval [0, 1];');

  // 如果存在解
  if (indicesCount == 1) {
    /**
     * 作如下转换
     * [[n1, n2]] ==> [n1, n2]，保持与题目相一致的输出
     */
    indices = indices[0];

    const { numbers, target } = twoSum;
    const [index1, index2] = indices;
    const number1 = numbers[index1];
    const number2 = numbers[index2];

    // 索引对应的两个数加和应该等于`target`
    it.deepEqual(number1 + number2, target,
      'Indices of the two numbers such that they add up to a specific target;');
  }
});
