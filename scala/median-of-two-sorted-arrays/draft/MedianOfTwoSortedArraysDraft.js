function getMiddle(count) {
  const middle = { one: 0, two: 0 };

  if (count % 2 === 0) {
    let one = count / 2;
    middle.one = one;
    middle.two = one + 1;
  } else {
    let one = Math.ceil(count / 2);
    middle.one = one;
    middle.two = one;
  }

  return middle;
}

function getMiddleElements(range) {
  const { start, end, count, arr } = range;
  const middle = getMiddle(count);
  const middleElements = { one: 0, two: 0 };

  const one = start + middle.one - 1;
  const two = start + middle.two - 1;

  middleElements.one = arr[one];
  middleElements.two = arr[two];

  return middleElements;
}

function getPointer(arr, index) {
  const value = arr[index];

  return {
    index,
    value,
  };
}

function getRange(arr) {
  const range = { start: null, end: null, count: 0, arr: null };
  const length = arr.length;

  range.start = getPointer(arr, index = 0);
  range.end = getPointer(arr, index = length - 1);

  range.count = length;
  range.arr = arr;

  return range;
}

function getTotalCount(arr1, arr2) {
  const length1 = arr1.length;
  const length2 = arr2.length;
  const count = length1 + length2;

  return count;
}

function getBinarySplitRange(range) {
  const range1 = { arr: null, start: null , end: null , count: 0 };
  const range2 = { arr: null, start: null , end: null , count: 0 };
  const { start, end, arr, count } = range;

  const middle = getMiddle(range.count);
  const middleElement = getMiddleElements(range);

  range1.start = start
  range2.end = end

  range1.arr = range.arr
  range2.arr = range.arr

  if (middle.one == middle.two) {
    const before = Math.floor(count / 2);

    range1.end = getPointer(arr, middle.one - 2);
    range1.count = before;
    range2.start = getPointer(arr, middle.one - 1);
    range2.count = before + 1;
  } else {
    range1.end = getPointer(arr, middle.one - 1);
    range1.count = count / 2;
    range2.start = getPointer(arr, middle.two - 1);
    range2.count = count / 2;
  }

  return [range1, range2];
}

function getBeforeSplitRange(range1, range2) {
  const { end } = range1;
  let range;

  if (range2.end.value < end.value) {
    range = range2;
  } else {
    let rangeList = getBinarySplitRange(range2);
    range = getBeforeSplitRange(range1, rangeList[0]);
  }

  return range;
}

function getRangeAfter(range, rangeBefore) {
  const { arr, count } = range;
  const rangeAfter = { arr, start: null , end: null , count: 0 };

  rangeAfter.count = range.count - rangeBefore.count;
  rangeAfter.start = getPointer(arr, count - rangeBefore.count);
  rangeAfter.end = range.end;

  return rangeAfter;
}

function getMiidleResult(range1, range2, middleNumber) {
  if (middleNumber === 1) {
    if (range1.count) {
      return range1.start.value;
    } else {
      return range2.start.value;
    }
  } else if (middleNumber === 2) {
    let one;
    let two;
    if (range1.count === 2) {
      one = range1.start;
      two = range1.end;
    }
    if (range2.count === 2) {
      one = range2.start;
      two = range2.end;
    }
    else {
      one = range1.start;
      two = range2.start;
    }
    return (two + one) / 2;
  }
}

function updateRange(range1, range2, before, after, middleNumber) {
  const count = getTotalCount(range1.count, range2.count);
  const middle = getMiddle(count);

  // 二分数组一
  const range1List = getBinarySplitRange(range1);

  const range1Before = range1List[0];
  const range2Before = getBeforeSplitRange(range1Before, range2);
  const beforeTotal = range1Before.count + range2Before.count;

  const range1After = range1List[1];
  const range2After = getRangeAfter(range2, range2Before);
  const afterTotal = range1After.count + range2After.count;

  if(beforeTotal == middleNumber) {
    return getMiidleResult(range1Before, range2Before, middleNumber);
  }

  if(afterTotal == middleNumber) {
    return getMiidleResult(range1After, range2After, middleNumber);
  }

  if (beforeTotal >= before + middleNumber) {
    let afterCount = after - afterTotal;
    return updateRange(range1Before, range2Before, before, afterCount,
      middleNumber);
  }

  if (afterTotal >= after + middleNumber) {
    let beforeCount = before - beforeTotal;
    return updateRange(range1After, range2After, beforeCount, after,
      middleNumber);
  }
}

function MedianOfTwoSortedArrays(arr1, arr2) {
  const count = getTotalCount(arr1, arr2);
  const middle = getMiddle(count);
  const range1 = getRange(arr1);
  const range2 = getRange(arr2);
  let middleNumber;
  let before;
  let after;

  if (middle.one == middle.two) {
    before = Math.floor(count / 2);
    after = Math.floor(count / 2);
    middleNumber = 1;
  } else {
    before = (count / 2) - 1;
    after = (count / 2) - 1;
    middleNumber = 2;
  }

  return updateRange(range1, range2, before, after, middleNumber);
}

function main() {
  const arr1 = [2, 4, 9, 10, 200, 1000, 1500];
  const arr2 = [-100, -70, -10, 0, 210, 300];

  const middle = MedianOfTwoSortedArrays(arr1, arr2);
  console.log(middle);
}

main();
