import { getSourceFile } from '../../lib/tools';

import test from 'ava';

test('Leetcode Problems: 1.Two Sum;', (it) => {
  // 自定义测试数据
  const TwoSum = getSourceFile(__filename);

  const twoSum = new TwoSum({
    numbers: [1, 2, 3, 4, 5, 6],
    target: 7,
  });

  // 验证算法运行结果
  twoSum.getIndices()
    .forEach((indice) => {
      const { numbers, target } = twoSum;
      const [index1, index2] = indice;
      const number1 = numbers[index1];
      const number2 = numbers[index2];

      // 索引对应的两个数加和应该等于`target`
      it.deepEqual(number1 + number2, target,
        'Indices of the two numbers such that they add up to a specific target.');
    });
});
