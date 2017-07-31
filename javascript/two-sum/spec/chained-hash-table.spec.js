/**
 * @file 验证链接散列表正确性
 */
import test from 'ava';

import ChainedHashTable from 'two-sum/src/chained-hash-table';

// 设置测试上下文
test.beforeEach((it) => {
  const context = it.context;

  context.hashTable = new ChainedHashTable();
});

test('Chained hash table: [Insert]', (it) => {
  const hashTable = it.context;
  let list;

  hashTable({ key: 1, value: 1 });
  list = hashTable.search({ key: 1 });
});

test('Chained hash table: [Search]', (it) => {
  const hashTable = it.context;
});

test('Chained hash table: [Delete]', (it) => {
  const hashTable = it.context;
});
