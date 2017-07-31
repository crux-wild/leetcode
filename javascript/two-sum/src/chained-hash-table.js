/**
 * 链接散列表实现
 * @class
 */
class ChainedHashTable {
  constructor() {
    this.hashTable = {};
  }

  /**
   * @public
   */
  insert({  key = 0, value = 0 }) {
    const { hashTable } = this;
    let slot = hashTable[key];

    if (slot === undefined) {
      // 对应槽位不存在，建立一个链表
      hashTable[key] = [value];
    } else {
      // 对应槽位存在，向链表添加元素
      slot.push(value);
    }

    return this;
  }

  /**
   * @public
   * @return {Array} -对应槽位包含数据链表
   */
  search({ key = 0 }) {
    return this.getList(key);
  }

  /**
   * @public
   */
  delete({ key = 0, value = 0 }) {
    const list = this.getList(key);

    list.forEach((item, index, list) => {
      if (item === value) {
        list.splice(index, 1);
      }
    });

    return this;
  }

  /**
   * @private
   * @return - 对应槽点的数据链表
   */
  getList(key) {
    const { hashTable } = this;
    const slot = hashTable[key];
    let list;

    if (slot === undefined) {
      list = [];
    } else {
      list = slot;
    }

    return list;
  }
}

export default ChainedHashTable;
