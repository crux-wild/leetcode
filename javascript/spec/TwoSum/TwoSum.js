import test from 'ava';

test('Leetcode Problems: 1.Two Sum', (it) => {
  // 获取源文件
  const sourceFile = __filename.replace(/\/spec\//, '\/src\/');
  const TwoSum = require(sourceFile).default;

  // 自定义测试数据
  const numbers = [1, 2, 3, 4, 5, 6];
  const target = 7;

  // 验证算法实例
  const twoSum = new TwoSum(numbers, target);
  const indices = twoSum.getIndices();

  indices.forEach((indice) => {
    const [ index1, index2 ] = indice;
    const number1 = numbers[index1];
    const number2 = numbers[index2];

    // 索引对应的两个加和应该等于`target`
    const sumOfTwoNumber = number1 + number2;
     it.deepEqual(sumOfTwoNumber, target,
      'Indices of the two numbers such that they add up to a specific target.');
  });
});
