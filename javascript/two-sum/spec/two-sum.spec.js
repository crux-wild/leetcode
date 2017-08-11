/**
 * @file 1.Two Sum 算法正确性验证单元测试
 * @see https://leetcode.com/problems/two-sum/#/description
 */
import test from 'ava';

import TwoSum from 'two-sum/src/two-sum';

// 设置测试上下文
test.beforeEach((it) => {
  const context = it.context;

  context.options = { numbers: [1, 2, 3, 4, 5, 6] };
});

// 测试多解情况
test('Leetcode Problems: 1.Two Sum[multiple solutions];', (it) => {
  const options = { isMultiple: true, target: 7 };
  const twoSum = new TwoSum({ ...it.context.options, ...options });

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

// 测试单解情况
test('Leetcode Problems: 1.Two Sum[single solution];', (it) => {
  // 自定义测试数据
  const options = { isMultiple: false, target: 7 };
  const twoSum = new TwoSum({ ...it.context.options, ...options });

  let indices = twoSum.getIndices();
  const indicesCount = indices.length;

  // 如果只需要求解一个问题，那么解的个数应该在[0, 1]区间内
  it.true((indicesCount >= 0 && indicesCount <= 1),
    'indicesCount shoule located within the interval [0, 1];');

  // 如果存在解
  if (indicesCount === 1) {
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

// 测试无解情况情况
test('Leetcode Problems: 1.Two Sum[none solution];', (it) => {
  // 自定义测试数据
  const options = { isMultiple: true, target: 0 };

  /**
   * 不满足`number1 + number2 = target`的情况，应该抛出异常
   * `InvalidArgumentException`是私有的，所以这里断言错误信息这个表征
   * @see https://github.com/avajs/ava#throwsfunctionpromise-error-message
   */
  it.throws(() => {
    new TwoSum({ ...it.context.options, ...options });
  }, 'No two sum solution;');
});
