## 时间复杂度分析

## 单次运算分析

### 算法上下界

|  算法/操作 | constructor()     | getProp()          | setProp()           |
|------------|-------------------|--------------------|---------------------|
|  直接同步  | O(n)              | O(1)               | O(n)                |
|  需时同步  | O(1)              | O(1) ~ O(n)        | O(1)                |

- 注1： 变量n具体是指这次更新链表的链表长度


## 多次运算分析

### 模型抽象

对象`digitStringList`多次操作可以抽象为:

```
<-- 构造过程0 -->  <-- get操作组0 -->  <-- set操作组0 -->  <-- get操作组1 -->  <-- set操作组0 -->
    construct0,   [get00, get01 ...], [set00, set01 ...], [get10, get11 ...], [set10, set11 ...] ...
```

- 注1： 其中`get`操作和`set`操作组顺序可以任意组合，每组包含操作可以有任意多个

### 分析过程

总时间成本:

```
fn(n) = c0 + ∑(g01 + ... + g0n) + Σ(s00 + ... + s0n) +  ...

```

- 注1：fn(n)中的n是总的操作的个数
- 注3： 一个变量的生命周期只会存在一次构造过程，构造时间就是c0
- 注2： 其中变量c0指第一次构造函数时间，g00是第零组第零次操作的时间, s00是第零组第零次的时间

### 摊还成本

|  算法      | 摊还上界    |
|------------|-------------|
|  直接同步  | O(k)        |
|  需要同步  | O(k)/2      |

注1: 严格意义上级数上界是一致的，但是常数级别减少一半

### 直接同步

总时间成本:

```
fn(n) = c0 + gn * 1 + ∑(s00 + ... + s0n + ... + sn0 + ... + snn)
```

- 注1：fn(n)中的n是总的操作的个数
- 注2：gn是所有get操作的总个数，其中每个get操作的成本是1

上界分析:

```
f(n) <= c0 + gn * 1 + sn * max(s00 ~ snn) <= c0 + (n - 1) * max(s00 ~ snn)

O(n)/n = (O(k) + (n - 1) * O(k))/n = O(k)
```

- 注1: 其中sn是set操作的总个数
- 注2: 单次操作set操作比get操作开销
- 注3: 其中k更新链表的节点个数，n是一个操作的共个数

### 需时同步

时间总成本：

```
fn(n) = 1 + sn * 1 + ∑(g00 + ... + g0n) + ... + ∑(gn0 + ... + gnn)

fn(n) = 1 + sn * 1 + ∑(g00 + 1 + ... + 1) + ... + ∑(gn0 + 1 + ... + 1)
```

- 注1: 多次get操作除了第一次意外，其他成本都是1

每组get操作的第一次成本:

```
  | gn0 = 1 (不需要更新)
 -|
  | gn0 = last(k) (需要更新)
```

注1: 其中gn0开销等于其之前一组set操作最后一个的开销

上界分析:

```
f(n) <= 1 + (1 + max(k)) * ((n - 1)/2)

f(n) <= max(k) * (n - 1)/2 + ((n + 1)/2)

f(n) <= max(k) * (n - 1)/2 + n/2 * max(k) + 1/2 = max(k) * 1/2 + 1/2

O(n)/n = O(k)/2
```

注1: 上述操作需要更新gn0成本更高，但是前面必须有一次set操作触发更新状态扭转
