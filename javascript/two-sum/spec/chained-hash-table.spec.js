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
  const { hashTable }= it.context;
  const value = 1;
  const key = 1;
  let list;

  // 插入一个不存在的键，自动链表保存对应值
  list = hashTable
    .insert({ key, value })
    .search({ key });

  it.deepEqual(list, [value],
    '[HashTable] After first insert value, list should be equals [value]');
});

test('Chained hash table: [Delete]', (it) => {
  const { hashTable }= it.context;
  const value1 = 1;
  const value2 = 2;
  const key = 1;
  let list;

  list = hashTable
    .insert({ key, value: value1 })
    .insert({ key, value: value2 })
      .search({ key });

  it.deepEqual(list, [value1, value2],
    '[HashTable] After insert [value1, value2], list should be [value1, value2]');

  // 删除可以对应键和值，删除对应键的链表中一项
  list = hashTable
    .delete({ key, value: value1 })
    .search({ key });

  it.deepEqual(list, [value2],
    '[HashTable] After delete value1, list should be [value2]');

  // 删除可以对应键和值，删除对应键的链表中一项
  list = hashTable
    .delete({ key, value: value2 })
    .search({ key });

  it.deepEqual(list, [],
    '[HashTable] After delete value2, list should be []');
});

test('Chained hash table: [Search]', (it) => {
  const { hashTable }= it.context;
  const key = 1;
  let list;

  list = hashTable.search({ key });

  // 查找不存在的键值应该返回空数组
  it.deepEqual(list, [],
    '[HashTable] search unexist key, list should be []');
});
